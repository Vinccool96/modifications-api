package io.github.vinccool96.modifications.api.modifications;

public abstract class ModificationBase<E> implements Modification<E> {

    private String description;

    protected E element;

    public ModificationBase(String description, E element) {
        this.description = description;
        this.element = element;
    }

    public String getDescription() {
        return description;
    }

}
