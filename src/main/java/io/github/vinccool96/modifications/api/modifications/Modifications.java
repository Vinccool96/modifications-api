package io.github.vinccool96.modifications.api.modifications;

import java.util.LinkedList;

public class Modifications {

    private LinkedList<Modification> modifications;

    private int position;

    public Modifications() {
        this.modifications = new LinkedList<>();
        this.position = -1;
    }

    public void undo() {
        if (this.position != -1) {
            this.modifications.get(this.position--).undo();
        }
    }

    public void redo() {
        if (this.position != this.modifications.size() - 1) {
            this.modifications.get(++this.position).redo();
        }
    }

    @SuppressWarnings("ListRemoveInLoop")
    public void addModification(Modification modification) {
        if (modification.isUseful()) {
            if (this.position != this.modifications.size() - 1) {
                for (int i = this.modifications.size() - 1; i > this.position; i--) {
                    this.modifications.remove(i);
                }
            }
            modifications.add(modification);
            position++;
        }
    }

}
