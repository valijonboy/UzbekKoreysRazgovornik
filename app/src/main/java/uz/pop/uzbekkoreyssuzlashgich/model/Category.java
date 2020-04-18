package uz.pop.uzbekkoreyssuzlashgich.model;

public class Category {

    int id, image, parent_id;
    String title;

    public Category() {
    }

    public Category(int id, int image, int parent_id, String title) {
        this.id = id;
        this.image = image;
        this.parent_id = parent_id;
        this.title = title;
    }

    public Category(int image, int parent_id, String title) {
        this.image = image;
        this.parent_id = parent_id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
