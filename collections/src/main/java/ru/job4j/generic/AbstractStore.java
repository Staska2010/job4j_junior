package ru.job4j.generic;

public class AbstractStore<T extends Base> implements Store<T> {
    private final SimpleArray<T> store;

    public AbstractStore(int size) {
        store = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int position = findPosition(id);
        return position == -1 ? false : store.set(position, model);
    }

    @Override
    public boolean delete(String id) {
        int position = findPosition(id);
        if (position == -1) {
            return false;
        } else {
            store.remove(position);
            return true;
        }
    }

    @Override
    public T findById(String id) {
        int position = findPosition(id);
        return position == -1 ? null : store.get(position);
    }

    /**
     * Find position of Model in store by Id
     *
     * @param id - String id of model
     * @return position in store
     */
    private int findPosition(String id) {
        int position = -1;
        for (int i = 0; i < store.getLength(); i++) {
            if (store.get(i).getId().equals(id)) {
                position = i;
                break;
            }
        }
        return position;
    }
}
