package io.github.vinccool96.modifications.api.modifications;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

abstract public class MultipleModification<E, M extends ModificationBase<E>> extends ModificationBase<ArrayList<M>> {

    public MultipleModification(String description, ArrayList<M> element) {
        super(description, element);
    }

    public MultipleModification(String description, List<E> elements, Class<? extends M> modificationClass,
            Class<?>[] types, Object[] args) {
        super(description, new ArrayList<>());
        for (E element : elements) {
            try {
                M modification = modificationClass.getConstructor(types).newInstance(args);
                getModifications().add(modification);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    protected ArrayList<M> getModifications() {
        return this.element;
    }

}
