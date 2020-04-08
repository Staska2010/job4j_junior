package ru.job4j.test;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class MailAnalyzer {

    public List<User> analyze(@NotNull List<User> users) {
        Map<String, User> mailToUserMapping = new HashMap<>();
        Map<User, Set<String>> usersMap = new HashMap<>();

        for (User user : users) {
            for (String nextMail : user.getMails()) {
                usersMap.putIfAbsent(user, new HashSet<>());
                if (!mailToUserMapping.containsKey(nextMail)) {
                    mailToUserMapping.put(nextMail, user);
                    usersMap.get(user).add(nextMail);
                } else {
                    User duplicateUser = mailToUserMapping.get(nextMail);
                    usersMap.get(user).addAll(usersMap.get(duplicateUser));
                    for (String duplUserMails : usersMap.get(user)) {
                        mailToUserMapping.replace(duplUserMails, user);
                    }
                    usersMap.remove(duplicateUser);
                }
            }
        }
        return outputResult(usersMap);
    }

    private List<User> outputResult(Map<User, Set<String>> usersMap) {
        List<User> result = usersMap.entrySet().stream()
                .map(x -> new User(x.getKey().getName(), x.getValue()))
                .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }
}
