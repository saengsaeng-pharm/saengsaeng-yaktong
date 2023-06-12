package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.api.ProductInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.SearchSuggestionItemBinding;

public class SearchSuggestionsAdapter extends ListAdapter<ProductInfo, SearchSuggestionsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final SearchSuggestionItemBinding binding;
        private ProductInfo item;

        public ViewHolder(SearchSuggestionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setItem(ProductInfo item) {
            this.item = item;
            binding.setItem(item);
        }
    }

    public interface OnClickListener {
        void onClick(ProductInfo item);
    }

    private OnClickListener onClickListener;

    public SearchSuggestionsAdapter() {
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
        final SearchSuggestionItemBinding binding = SearchSuggestionItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
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
