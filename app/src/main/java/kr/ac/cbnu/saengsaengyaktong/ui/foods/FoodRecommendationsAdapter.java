package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import kr.ac.cbnu.saengsaengyaktong.api.ProductInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.FoodRecommendationItemBinding;
import kr.ac.cbnu.saengsaengyaktong.databinding.SearchSuggestionItemBinding;

public class FoodRecommendationsAdapter extends ListAdapter<ProductInfo, FoodRecommendationsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FoodRecommendationItemBinding binding;
        private ProductInfo item;

        public ViewHolder(FoodRecommendationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setItem(ProductInfo item) {
            this.item = item;
            binding.setItem(item);

            final Random random = new Random();
            float randomFloat = random.nextFloat();
            float scaledRandomFloat = 3 + randomFloat * (5 - 3);

            binding.setRating(scaledRandomFloat);
        }
    }

    public interface OnClickListener {
        void onClick(ProductInfo item);
    }

    private OnClickListener onClickListener;

    public FoodRecommendationsAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setItems(List<? extends ProductInfo> items) {
        submitList((List<ProductInfo>) items);
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final ProductInfo item = getItem(position);
        viewHolder.setItem(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final FoodRecommendationItemBinding binding = FoodRecommendationItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(viewHolder.item);
            }
        });

        return viewHolder;
    }

    public static final DiffUtil.ItemCallback<ProductInfo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ProductInfo>() {
                @Override
                public boolean areItemsTheSame(@NonNull ProductInfo oldItem, @NonNull ProductInfo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull ProductInfo oldItem, @NonNull ProductInfo newItem) {
                    return oldItem.getId().equals(newItem.getId()); // should be fixed
                }
            };
}
