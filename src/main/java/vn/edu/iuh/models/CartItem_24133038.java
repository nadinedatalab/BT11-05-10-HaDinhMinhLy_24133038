package vn.edu.iuh.models;

public class CartItem_24133038 {
    private Video_24133038 video;
    private int quantity;

    public CartItem_24133038() {
    }

    public CartItem_24133038(Video_24133038 video, int quantity) {
        this.video = video;
        this.quantity = quantity;
    }

    public Video_24133038 getVideo() {
        return video;
    }

    public void setVideo(Video_24133038 video) {
        this.video = video;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return video.getPrice() * quantity;
    }
}
