package com.example.pruebaonoff.elementos;

class Coments {

    private int postId;
    private int id;
    private String body;

    public Coments(int postId, int id, String body) {
        this.postId = postId;
        this.id = id;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
