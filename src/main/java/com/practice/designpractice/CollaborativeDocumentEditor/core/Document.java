package com.practice.designpractice.CollaborativeDocumentEditor.core;

import com.practice.designpractice.CollaborativeDocumentEditor.observer.ClientObserver;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Document {
    private final String id;
    @Getter
    private String content;
    private final List<String> versionHistory;
    private final List<ClientObserver> subscribers;

    public Document(String id){
        this.id = id;
        this.content = "";
        this.versionHistory = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        versionHistory.add(content);
    }

    public void applyEdit(EditOperation op){
        this.content = op.apply(content);
        versionHistory.add(content);
        notifySubscribers();
    }

    public void subscribe(ClientObserver client) {
            subscribers.add(client);
    }


    public List<String> getVersionHistory() {
        return Collections.unmodifiableList(versionHistory);
    }

    private void notifySubscribers() {
        for (ClientObserver client : subscribers) {
            client.notifyChange(id, content);
        }
    }

}
