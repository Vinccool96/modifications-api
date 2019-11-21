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

    @Override
    public void undo() {
        for (M modification : this.getModifications()) {
            modification.undo();
        }
    }

    @Override
    public void redo() {
        for (M modification : this.getModifications()) {
            modification.redo();
        }
    }

    @Override
    public boolean isUseful() {
        boolean useful = true;
        for (M modification : this.getModifications()) {
            if (!modification.isUseful()) {
                useful = false;
                break;
            }
        }
        return useful;
    }

}
