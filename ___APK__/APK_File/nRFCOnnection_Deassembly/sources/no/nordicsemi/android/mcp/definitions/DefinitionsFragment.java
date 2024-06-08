package no.nordicsemi.android.mcp.definitions;

import a.k.a.a;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.e;
import androidx.recyclerview.widget.g;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter;

/* loaded from: classes.dex */
public class DefinitionsFragment extends Fragment implements a.InterfaceC0025a<Cursor> {
    private static final String ARG_ADOPTED = "adopted";
    private static final String ARG_TAB = "tab";
    private static final String ARG_TYPE = "type";
    private static final String ARG_URI = "uri";
    private static final String ARG_WITH_FORMAT = "with_format";
    private static final int LOADER_ID = 20;
    private CursorRecyclerAdapter mAdapter;
    private View mEmptyView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.definitions.DefinitionsFragment$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$mcp$definitions$DefinitionsFragment$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$mcp$definitions$DefinitionsFragment$Type[Type.SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$definitions$DefinitionsFragment$Type[Type.CHARACTERISTIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum Type {
        SERVICE,
        CHARACTERISTIC,
        DESCRIPTOR;

        /* JADX INFO: Access modifiers changed from: package-private */
        public String uriPart() {
            int i = AnonymousClass1.$SwitchMap$no$nordicsemi$android$mcp$definitions$DefinitionsFragment$Type[ordinal()];
            return i != 1 ? i != 2 ? "Descriptors" : "Characteristics" : "Services";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DefinitionsFragment getInstance(Uri uri, boolean z, Type type, boolean z2, int i) {
        DefinitionsFragment definitionsFragment = new DefinitionsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        bundle.putParcelable(ARG_URI, uri);
        bundle.putBoolean(ARG_ADOPTED, z);
        bundle.putBoolean(ARG_WITH_FORMAT, z2);
        bundle.putInt(ARG_TAB, i);
        definitionsFragment.setArguments(bundle);
        return definitionsFragment;
    }

    @Override // a.k.a.a.InterfaceC0025a
    public a.k.b.c<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable(ARG_URI);
        boolean z = bundle.getBoolean(ARG_ADOPTED);
        boolean z2 = bundle.getBoolean(ARG_WITH_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append("type IS");
        sb.append(z ? " NOT" : "");
        sb.append(" NULL");
        return new a.k.b.b(requireContext(), uri, z2 ? DefinitionsAdapter.PROJECTION_WITH_FORMAT : DefinitionsAdapter.PROJECTION, sb.toString(), null, "name ASC");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_definitions_list, viewGroup, false);
    }

    @Override // a.k.a.a.InterfaceC0025a
    public /* bridge */ /* synthetic */ void onLoadFinished(a.k.b.c cVar, Object obj) {
        onLoadFinished((a.k.b.c<Cursor>) cVar, (Cursor) obj);
    }

    @Override // a.k.a.a.InterfaceC0025a
    public void onLoaderReset(a.k.b.c<Cursor> cVar) {
        this.mAdapter.changeCursor(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mEmptyView = view.findViewById(android.R.id.empty);
        Bundle arguments = getArguments();
        Type type = (Type) arguments.getSerializable("type");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new g(recyclerView.getContext(), 1));
        recyclerView.setItemAnimator(new e());
        recyclerView.setHasFixedSize(true);
        DefinitionsAdapter definitionsAdapter = new DefinitionsAdapter(type);
        this.mAdapter = definitionsAdapter;
        recyclerView.setAdapter(definitionsAdapter);
        a.k.a.a.a(this).a(arguments.getInt(ARG_TAB) + 20, arguments, this);
    }

    public void onLoadFinished(a.k.b.c<Cursor> cVar, Cursor cursor) {
        this.mAdapter.swapCursor(cursor);
        this.mAdapter.notifyDataSetChanged();
        this.mEmptyView.setVisibility(this.mAdapter.getItemCount() == 0 ? 0 : 8);
    }
}
