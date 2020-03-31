package ru.job4j.test;

import java.util.*;

public class MailAnalyzer {

    public List<User> analyze(List<User> users) {
        List<User> result = new ArrayList<>();
        Map<String, Set<String>> mailsGraph = new HashMap<>();
        Map<String, String> mailToNameMap = new HashMap<>();

        for (User user : users) {
            String name = user.getName();
            for (int i = 0; i < user.getMails().size(); i++) {
                String mail = user.getMails().get(i);
                mailToNameMap.put(mail, name);
                mailsGraph.putIfAbsent(mail, new HashSet<>());
                if (i == 0) {
                    continue;
                }
                String previousMail = user.getMails().get(i - 1);
                mailsGraph.get(previousMail).add(mail);
                mailsGraph.get(mail).add(previousMail);
            }
        }

        Set<String> visited = new HashSet<>();
        for (String mail: mailToNameMap.keySet()) {
            User tempUser = new User();
            if (visited.add(mail)) {
                dfs(tempUser.getMails(), mail, mailsGraph, visited);
                tempUser.setName(mailToNameMap.get(mail));
                List<String> sortedMails = tempUser.getMails();
                Collections.sort(sortedMails);
                tempUser.setMails(sortedMails);
                result.add(tempUser);
            }
        }
        return result;
    }

    private void dfs(List<String> userMails, String mail, Map<String, Set<String>> mailsGraph, Set<String> visited) {
        userMails.add(mail);
        if (mailsGraph.get(mail).size() == 0) {
            return;
        }
        for (String nextNode: mailsGraph.get(mail)) {
            if (visited.add(nextNode)) {
                dfs(userMails, nextNode, mailsGraph, visited);
            }
        }
    }
}
