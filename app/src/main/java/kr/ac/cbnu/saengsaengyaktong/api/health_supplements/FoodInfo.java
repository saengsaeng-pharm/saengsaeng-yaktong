package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.stream.Collectors;

import kr.ac.cbnu.saengsaengyaktong.api.ProductInfo;
import kr.ac.cbnu.saengsaengyaktong.api.ProductType;

public class FoodInfo implements ProductInfo, Parcelable {
    private static final String THUMBNAIL_PREFIX = "https://health-functional-food.s3.ap-northeast-2.amazonaws.com/saved/";

    @Override
    public ProductType getType() {
        return ProductType.HEALTH_FOOD;
    }

    @SerializedName("report_no")
    private String id;

    private String name;

    @SerializedName("company")
    private String companyName;

    private List<String> functionalities;

    private List<String> materials;

    @SerializedName("warn")
    private List<String> cautions;

    @Nullable
    private List<String> thumbnails;

    @SerializedName("views")
    private int views;

    private FoodInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        companyName = in.readString();
        functionalities = in.createStringArrayList();
        materials = in.createStringArrayList();
        cautions = in.createStringArrayList();
        thumbnails = in.createStringArrayList();
        views = in.readInt();
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    public List<FunctionalityType> getFunctionalities() {
        return functionalities.stream().map(FunctionalityTypes::getById).collect(Collectors.toList());
    }

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getCautions() {
        return cautions;
    }

    @Nullable
    public List<String> getThumbnails() {
        if (thumbnails == null) return null;
        return thumbnails.stream().map(thumbnail -> THUMBNAIL_PREFIX + id + "/" + thumbnail).collect(Collectors.toList());
    }

    @Override
    public String getImageUrl() {
        final List<String> thumbnails = getThumbnails();

        if (thumbnails == null || thumbnails.isEmpty()) return null;
        return thumbnails.get(0);
    }

    public int getViews() {
        return views;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(name);
        out.writeString(companyName);
        out.writeStringList(functionalities);
        out.writeStringList(materials);
        out.writeStringList(cautions);
        out.writeStringList(thumbnails);
        out.writeInt(views);
    }

    public static final Parcelable.Creator<FoodInfo> CREATOR = new Parcelable.Creator<FoodInfo>() {
        public FoodInfo createFromParcel(Parcel in) {
            return new FoodInfo(in);
        }

        public FoodInfo[] newArray(int size) {
            return new FoodInfo[size];
        }
    };
}
