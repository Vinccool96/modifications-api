package io.github.vinccool96.modifications.api.modifications;

public interface Modification<E> {

    void undo();

    void redo();

    boolean isUseful();

}
