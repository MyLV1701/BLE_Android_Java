package no.nordicsemi.android.mcp.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.e;
import androidx.recyclerview.widget.g;
import androidx.recyclerview.widget.j;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.fragment.AlertDialogFragment;
import no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter;
import no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter;
import no.nordicsemi.android.mcp.widget.RemovableItemTouchHelperCallback;
import no.nordicsemi.android.mcp.widget.RemovableViewHolder;

/* loaded from: classes.dex */
public class ManageFavoritesFragment extends Fragment implements ItemTouchHelperAdapter {
    private CursorRecyclerAdapter mAdapter;
    private DatabaseHelper mDatabaseHelper;
    private View mEmptyView;

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = this.mAdapter.getItemCount() == 0;
        menuInflater.inflate(R.menu.favorites, menu);
        menu.findItem(R.id.action_clear).setVisible(!z);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_manage_favorites, viewGroup, false);
    }

    @Override // no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter
    public void onItemDismiss(RemovableViewHolder removableViewHolder) {
        int adapterPosition = removableViewHolder.getAdapterPosition();
        this.mDatabaseHelper.removeFavoriteDevice(this.mAdapter.getItemId(adapterPosition));
        this.mAdapter.swapCursor(this.mDatabaseHelper.getFavoritesDevices());
        this.mAdapter.notifyItemRemoved(adapterPosition);
        if (this.mAdapter.getItemCount() == 0) {
            this.mEmptyView.setVisibility(0);
            requireActivity().invalidateOptionsMenu();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.action_clear) {
            if (itemId != R.id.action_help) {
                return false;
            }
            AlertDialogFragment.getInstance(R.string.alert_favorite_title, R.string.alert_favorite_info, R.drawable.ic_help_dark).show(getChildFragmentManager(), (String) null);
            return true;
        }
        int itemCount = this.mAdapter.getItemCount();
        this.mDatabaseHelper.clearFavorites();
        this.mAdapter.changeCursor(null);
        this.mAdapter.notifyItemRangeRemoved(0, itemCount);
        this.mEmptyView.setVisibility(0);
        requireActivity().invalidateOptionsMenu();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mEmptyView = view.findViewById(android.R.id.empty);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new g(requireContext(), 1));
        recyclerView.setItemAnimator(new e());
        recyclerView.setHasFixedSize(true);
        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        this.mDatabaseHelper = databaseHelper;
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(databaseHelper.getFavoritesDevices());
        this.mAdapter = favoritesAdapter;
        recyclerView.setAdapter(favoritesAdapter);
        new j(new RemovableItemTouchHelperCallback(this)).a(recyclerView);
        setHasOptionsMenu(true);
        this.mEmptyView.setVisibility(this.mAdapter.getItemCount() == 0 ? 0 : 8);
    }
}
