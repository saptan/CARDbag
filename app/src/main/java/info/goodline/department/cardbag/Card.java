package info.goodline.department.cardbag;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable{

    private int id;
    private Category category;
    private String name;
    private double discount;

    public Card(int id, Category category, String name, double discount) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.discount = discount;
    }

    protected Card(Parcel in) {
        id = in.readInt();
        category = in.readParcelable(Category.class.getClassLoader());
        name = in.readString();
        discount = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(category, flags);
        dest.writeString(name);
        dest.writeDouble(discount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountFormated() {
        return discount + "%";
    }
}
