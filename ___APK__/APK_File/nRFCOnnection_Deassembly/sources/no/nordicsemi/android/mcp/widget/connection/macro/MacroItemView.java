package no.nordicsemi.android.mcp.widget.connection.macro;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.List;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.connection.macro.domain.MacroHandler;

/* loaded from: classes.dex */
public class MacroItemView extends LinearLayout implements View.OnClickListener {
    private static final int MAX_ROWS_VISIBLE = 6;
    private static final String TAG = "MacroItemView";
    private View mActionsView;
    private OperationsAdapter mAdapter;
    private MacroViewAnimator mAnimator;
    private WeakReference<IBluetoothLeConnection> mConnection;
    private int mCurrentMacroId;
    private TextView mErrorView;
    private ImageView mIconView;
    private View mLoadingInProgressView;
    private CheckBox mLoopOption;
    private Macro mMacro;
    private ViewGroup mMacroActionsListView;
    private View mMainView;
    private ImageButton mMoreAction;
    private TextView mNameView;
    private RecyclerView mOperationsView;
    private ImageButton mPlayAction;
    private ImageButton mRestartAction;
    private ImageButton mSkipAction;
    private ImageButton mStepOverAction;
    private final Runnable restartTask;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class OperationHolder extends RecyclerView.d0 {
        private TextView operation;

        OperationHolder(View view) {
            super(view);
            this.operation = (TextView) view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class OperationsAdapter extends RecyclerView.g<OperationHolder> {
        private List<String> mIssues;
        private Macro mMacro;
        private MacroHandler mMacroHandler;

        private OperationsAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            List operations;
            List<String> list = this.mIssues;
            if (list == null || list.isEmpty()) {
                Macro macro = this.mMacro;
                if (macro == null) {
                    return 0;
                }
                operations = macro.getOperations();
            } else {
                operations = this.mIssues;
            }
            return operations.size();
        }

        void setMacro(Macro macro, List<String> list, MacroHandler macroHandler) {
            this.mMacro = macro;
            this.mMacroHandler = macroHandler;
            this.mIssues = list;
            notifyDataSetChanged();
        }

        void setMacroHandler(MacroHandler macroHandler) {
            this.mMacroHandler = macroHandler;
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(OperationHolder operationHolder, int i) {
            TextView textView = operationHolder.operation;
            if (this.mIssues.isEmpty()) {
                textView.setText(this.mMacro.getOperations().get(i).getDescription());
                textView.setEnabled(true);
                MacroHandler macroHandler = this.mMacroHandler;
                if (macroHandler != null) {
                    textView.setBackgroundResource(macroHandler.getOperationStatus(i).res);
                    return;
                } else {
                    textView.setBackgroundResource(MacroHandler.OperationStatus.NOT_STARTED.res);
                    return;
                }
            }
            textView.setBackgroundResource(MacroHandler.OperationStatus.FAILED.res);
            textView.setText(this.mIssues.get(i));
            textView.setEnabled(false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public OperationHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new OperationHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.macro_action_item, viewGroup, false));
        }
    }

    public MacroItemView(Context context) {
        this(context, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartMacro() {
        this.mRestartAction.setEnabled(false);
        this.mPlayAction.setEnabled(true);
        showPlayAndPauseActions(false, false, true);
        this.mConnection.get().untrackMacro(this.mCurrentMacroId);
        this.mLoopOption.setEnabled(true);
        this.mAdapter.setMacroHandler(null);
    }

    private void setupClickListeners() {
        View findViewById = findViewById(R.id.macro_main);
        this.mMainView = findViewById;
        findViewById.setOnClickListener(this);
        this.mLoopOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.e
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MacroItemView.this.a(compoundButton, z);
            }
        });
        this.mPlayAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MacroItemView.this.a(view);
            }
        });
        this.mStepOverAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MacroItemView.this.b(view);
            }
        });
        this.mSkipAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MacroItemView.this.c(view);
            }
        });
        this.mRestartAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MacroItemView.this.d(view);
            }
        });
    }

    private void setupView() {
        this.mNameView = (TextView) findViewById(R.id.display_name);
        this.mLoadingInProgressView = findViewById(R.id.progress_indicator);
        this.mIconView = (ImageView) findViewById(R.id.icon);
        this.mMacroActionsListView = (ViewGroup) findViewById(R.id.actions_list);
        this.mErrorView = (TextView) this.mMacroActionsListView.findViewById(R.id.error);
        RecyclerView recyclerView = (RecyclerView) this.mMacroActionsListView.findViewById(android.R.id.list);
        this.mOperationsView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(null);
        recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.b
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return MacroItemView.this.a(view, motionEvent);
            }
        });
        OperationsAdapter operationsAdapter = new OperationsAdapter();
        this.mAdapter = operationsAdapter;
        recyclerView.setAdapter(operationsAdapter);
        this.mActionsView = findViewById(R.id.actions);
        this.mPlayAction = (ImageButton) findViewById(R.id.action_play);
        this.mMoreAction = (ImageButton) findViewById(R.id.action_more);
        this.mStepOverAction = (ImageButton) findViewById(R.id.action_step_over);
        this.mSkipAction = (ImageButton) findViewById(R.id.action_skip);
        this.mRestartAction = (ImageButton) findViewById(R.id.action_restart);
        this.mRestartAction.setEnabled(false);
        this.mLoopOption = (CheckBox) findViewById(R.id.option_loop);
        setupClickListeners();
    }

    private void showPlayAndPauseActions(boolean z, boolean z2, boolean z3) {
        this.mPlayAction.setVisibility(z3 ? 0 : 8);
        this.mPlayAction.setImageResource((z || z2) ? R.drawable.ic_action_pause : R.drawable.ic_action_play);
    }

    public /* synthetic */ boolean a(View view, MotionEvent motionEvent) {
        view.getParent().requestDisallowInterceptTouchEvent(this.mAdapter.getItemCount() > 6);
        return false;
    }

    public /* synthetic */ void b(View view) {
        MacroHandler macroStatus = this.mConnection.get().getMacroStatus(this.mCurrentMacroId);
        if (macroStatus == null) {
            macroStatus = this.mConnection.get().trackMacro(this.mCurrentMacroId, this.mMacro);
        }
        macroStatus.step();
    }

    public /* synthetic */ void c(View view) {
        MacroHandler macroStatus = this.mConnection.get().getMacroStatus(this.mCurrentMacroId);
        if (macroStatus == null) {
            macroStatus = this.mConnection.get().trackMacro(this.mCurrentMacroId, this.mMacro);
        }
        macroStatus.skip();
    }

    public /* synthetic */ void d(View view) {
        restartMacro();
    }

    public ViewGroup getExpandableView() {
        return this.mMacroActionsListView;
    }

    public Macro getMacro() {
        return this.mMacro;
    }

    public View getMainView() {
        return this.mMainView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.mAnimator.toggle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    public void setConnection(IBluetoothLeConnection iBluetoothLeConnection) {
        this.mConnection = new WeakReference<>(iBluetoothLeConnection);
    }

    public void setMacro(int i, Throwable th, MacroViewAnimator macroViewAnimator) {
        this.mCurrentMacroId = i;
        this.mMacro = null;
        this.mAnimator = macroViewAnimator;
        this.mMainView.setClickable(true);
        this.mNameView.setText(R.string.macros_error_title);
        this.mLoadingInProgressView.setVisibility(8);
        this.mIconView.setImageResource(R.drawable.ic_macro_icon_error);
        this.mIconView.setSelected(true);
        this.mIconView.setActivated(true);
        this.mActionsView.setVisibility(8);
        this.mPlayAction.setVisibility(8);
        this.mMoreAction.setVisibility(8);
        this.mErrorView.setText(getContext().getString(R.string.macros_deserialization_error, th.getLocalizedMessage()));
        this.mErrorView.setVisibility(0);
        this.mOperationsView.setVisibility(8);
        this.mAdapter.setMacro(null, null, null);
    }

    public void setMacroLoading(int i, String str, MacroViewAnimator macroViewAnimator) {
        this.mCurrentMacroId = i;
        this.mMacro = null;
        this.mAnimator = macroViewAnimator;
        this.mMainView.setClickable(false);
        this.mNameView.setText(str);
        this.mLoadingInProgressView.setVisibility(0);
        this.mIconView.setImageResource(0);
        this.mIconView.setSelected(false);
        this.mIconView.setActivated(false);
        showPlayAndPauseActions(false, false, false);
        this.mMoreAction.setVisibility(8);
        this.mActionsView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mOperationsView.setVisibility(8);
        this.mAdapter.setMacro(null, null, null);
    }

    public MacroItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        this.mAnimator.setLoopEnabled(z);
    }

    public MacroItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentMacroId = 0;
        this.restartTask = new Runnable() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.f
            @Override // java.lang.Runnable
            public final void run() {
                MacroItemView.this.restartMacro();
            }
        };
    }

    public /* synthetic */ void a(View view) {
        MacroHandler macroStatus = this.mConnection.get().getMacroStatus(this.mCurrentMacroId);
        if (macroStatus == null) {
            macroStatus = this.mConnection.get().trackMacro(this.mCurrentMacroId, this.mMacro);
        }
        if (!macroStatus.isOperationInProgress()) {
            this.mLoopOption.setEnabled(false);
            macroStatus.play(this.mLoopOption.isChecked());
        } else {
            this.mLoopOption.setEnabled(true);
            macroStatus.stop();
        }
    }

    public void setMacro(int i, Macro macro, MacroViewAnimator macroViewAnimator) {
        this.mCurrentMacroId = i;
        this.mMacro = macro;
        this.mAnimator = macroViewAnimator;
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection.get();
        MacroHandler macroStatus = iBluetoothLeConnection.getMacroStatus(i);
        List<String> compatibilityIssues = macro.getCompatibilityIssues(iBluetoothLeConnection);
        boolean z = macroStatus != null;
        boolean isEmpty = compatibilityIssues.isEmpty();
        boolean areServicesDiscovered = iBluetoothLeConnection.areServicesDiscovered();
        boolean isRecorded = macro.isRecorded();
        boolean isOperationInProgress = iBluetoothLeConnection.isOperationInProgress();
        boolean z2 = z && macroStatus.getCurrentOperationIndex() == -1;
        boolean z3 = (z && macroStatus.isOperationInProgress()) ? false : true;
        boolean z4 = z && macroStatus.isOperationInProgress();
        boolean isMacroRunning = iBluetoothLeConnection.isMacroRunning();
        boolean z5 = isMacroRunning && macroStatus != null;
        if (z) {
            macroStatus.isFinished();
        }
        this.mMainView.setClickable(true);
        TextView textView = this.mNameView;
        textView.setText(!isRecorded ? macro.getName() : textView.getContext().getString(R.string.macros_title_recording));
        this.mLoadingInProgressView.setVisibility(8);
        this.mIconView.setImageResource(!isRecorded ? macro.getIconRes() : R.drawable.ic_macro_new);
        this.mIconView.setSelected(isEmpty && areServicesDiscovered);
        this.mIconView.setActivated(isRecorded);
        showPlayAndPauseActions(z4, z5, isEmpty && areServicesDiscovered && !isRecorded);
        this.mPlayAction.setEnabled(!isOperationInProgress || z4);
        this.mMoreAction.setVisibility(!isRecorded ? 0 : 8);
        this.mActionsView.setVisibility(!isRecorded ? 0 : 8);
        this.mStepOverAction.setEnabled(!isOperationInProgress && isEmpty && areServicesDiscovered && !isMacroRunning && z3);
        this.mSkipAction.setEnabled(!isOperationInProgress && isEmpty && areServicesDiscovered && !isMacroRunning && z3);
        this.mLoopOption.setEnabled(!isOperationInProgress && isEmpty && areServicesDiscovered && !isMacroRunning && z3);
        this.mLoopOption.setChecked(macroViewAnimator.isLoopEnabled());
        this.mRestartAction.setEnabled(!isOperationInProgress && isEmpty && areServicesDiscovered && !isMacroRunning && z2);
        this.mErrorView.setVisibility(8);
        boolean z6 = compatibilityIssues.size() <= 0 ? macro.getOperations().size() > 6 : compatibilityIssues.size() > 6;
        int applyDimension = (int) TypedValue.applyDimension(1, 150.0f, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams layoutParams = this.mOperationsView.getLayoutParams();
        int i2 = layoutParams.height;
        if (!z6) {
            applyDimension = -2;
        }
        layoutParams.height = applyDimension;
        boolean z7 = i2 != layoutParams.height;
        this.mOperationsView.setLayoutParams(layoutParams);
        this.mOperationsView.setVisibility(0);
        this.mOperationsView.setBackgroundResource(z6 ? R.drawable.macros_actions_bg : 0);
        this.mAdapter.setMacro(macro, compatibilityIssues, macroStatus);
        if (macroStatus != null) {
            int max = Math.max(macroStatus.getLastCompletedOperationIndex() - 1, 0);
            int min = Math.min(macroStatus.getLastCompletedOperationIndex() + 1, this.mAdapter.getItemCount());
            if (((LinearLayoutManager) this.mOperationsView.getLayoutManager()).H() < max + 2) {
                this.mOperationsView.scrollToPosition(min);
            } else {
                this.mOperationsView.scrollToPosition(max);
            }
        } else if (isRecorded && macro.getOperations().size() > 6) {
            this.mOperationsView.scrollToPosition(macro.getOperations().size() - 1);
        }
        if (isRecorded || z7) {
            macroViewAnimator.recalculateExpandableViewHeight();
        }
    }
}
