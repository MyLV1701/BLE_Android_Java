package no.nordicsemi.android.mcp.definitions;

import a.k.a.a;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.androidplot.util.PixelUtils;
import com.google.android.material.tabs.TabLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.fragment.AlertDialogFragment;
import no.nordicsemi.android.mcp.fragment.ProgressDialogFragment;
import no.nordicsemi.android.mcp.util.FileHelper;

/* loaded from: classes.dex */
public class DefinitionsActivity extends BaseActivity implements a.InterfaceC0025a<Boolean> {
    private static final String EXTRA_FILE_PATH = "no.nordicsemi.android.mcp.FILE_PATH";
    private static final String EXTRA_FILE_URI = "no.nordicsemi.android.mcp.FILE_URI";
    private static final int IMPORT_DEFINITIONS_LOADER_REQ = 99;
    private static final int REQUEST_IMPORT_JSON = 34;
    private static final int REQUEST_SAVE_ALL = 35;
    private static final int REQUEST_SAVE_USER = 36;

    private void exportDefinitions(boolean z) {
        String definitionsFileName = FileHelper.getDefinitionsFileName(z);
        if (Build.VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("application/json");
            intent.putExtra("android.intent.extra.TITLE", definitionsFileName);
            startActivityForResult(intent, z ? 36 : 35);
            return;
        }
        String json = JSONDefinitionConverter.toJSON(new DatabaseHelper(this), z);
        try {
            File file = new File(Environment.getExternalStorageDirectory(), FileHelper.NORDIC_FOLDER);
            if (!file.exists()) {
                file.mkdir();
            }
            File file2 = new File(file, definitionsFileName);
            file2.createNewFile();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2));
            outputStreamWriter.append((CharSequence) json);
            outputStreamWriter.close();
            Uri contentUri = FileHelper.getContentUri(this, file2);
            if (contentUri != null) {
                FileHelper.showFileNotification(this, contentUri, "application/json");
            }
        } catch (Exception e2) {
            Log.e("DefinitionsActivity", "Error while exporting configuration", e2);
            Toast.makeText(this, R.string.error_export_failed, 0).show();
        }
    }

    private void importDefinitions() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("application/json");
        startActivityForResult(intent, 34);
    }

    public /* synthetic */ void a() {
        ProgressDialogFragment.getInstance(R.string.definitions_importing_in_progress).show(getSupportFragmentManager(), "progress");
    }

    public /* synthetic */ void b() {
        androidx.fragment.app.c cVar = (androidx.fragment.app.c) getSupportFragmentManager().b("progress");
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.d, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data = intent != null ? intent.getData() : null;
        if (i2 == -1 && data != null) {
            switch (i) {
                case 34:
                    Bundle bundle = new Bundle();
                    if (data.getScheme().equals("file")) {
                        bundle.putString(EXTRA_FILE_PATH, data.getPath());
                        a.k.a.a.a(this).a(99, bundle, this);
                        return;
                    } else {
                        if (data.getScheme().equals(LogContract.Session.Content.CONTENT)) {
                            Bundle extras = intent.getExtras();
                            if (extras != null && extras.containsKey("android.intent.extra.STREAM")) {
                                data = (Uri) extras.getParcelable("android.intent.extra.STREAM");
                            }
                            bundle.putParcelable(EXTRA_FILE_URI, data);
                            a.k.a.a.a(this).a(99, bundle, this);
                            return;
                        }
                        return;
                    }
                case 35:
                case 36:
                    String json = JSONDefinitionConverter.toJSON(new DatabaseHelper(this), i == 36);
                    try {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContentResolver().openOutputStream(data));
                        outputStreamWriter.append((CharSequence) json);
                        outputStreamWriter.close();
                        Toast.makeText(this, R.string.file_saved, 0).show();
                        return;
                    } catch (Exception e2) {
                        Log.e("DefinitionsActivity", "Error while exporting configuration", e2);
                        Toast.makeText(this, R.string.error_export_failed, 0).show();
                        return;
                    }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_with_tabs);
        PixelUtils.init(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_actionbar));
        getSupportActionBar().d(true);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new TabsAdapter(this, getSupportFragmentManager()));
        viewPager.setPageMargin((int) PixelUtils.dpToPix(10.0f));
        ((TabLayout) findViewById(R.id.toolbar_tabs)).setupWithViewPager(viewPager);
    }

    @Override // a.k.a.a.InterfaceC0025a
    public a.k.b.c<Boolean> onCreateLoader(int i, Bundle bundle) {
        InputStream openInputStream;
        if (i == 99) {
            try {
                if (bundle.containsKey(EXTRA_FILE_PATH)) {
                    openInputStream = new FileInputStream(bundle.getString(EXTRA_FILE_PATH));
                } else {
                    openInputStream = getContentResolver().openInputStream((Uri) bundle.getParcelable(EXTRA_FILE_URI));
                }
                new Handler().post(new Runnable() { // from class: no.nordicsemi.android.mcp.definitions.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefinitionsActivity.this.a();
                    }
                });
                return new ImportDefinitionsLoader(this, openInputStream);
            } catch (IllegalStateException unused) {
                return null;
            } catch (Exception e2) {
                Log.e("DefinitionsActivity", "Loading new definitions failed", e2);
                Toast.makeText(this, R.string.error_import_failed, 1).show();
                return null;
            }
        }
        return null;
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.definitions, menu);
        return true;
    }

    @Override // a.k.a.a.InterfaceC0025a
    public /* bridge */ /* synthetic */ void onLoadFinished(a.k.b.c cVar, Object obj) {
        onLoadFinished((a.k.b.c<Boolean>) cVar, (Boolean) obj);
    }

    @Override // a.k.a.a.InterfaceC0025a
    public void onLoaderReset(a.k.b.c<Boolean> cVar) {
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_export /* 2131296357 */:
                exportDefinitions(false);
                return true;
            case R.id.action_export_user /* 2131296358 */:
                exportDefinitions(true);
                return true;
            case R.id.action_help /* 2131296362 */:
                AlertDialogFragment.getInstance(R.string.alert_definitions_title, R.string.alert_definitions_info, R.drawable.ic_help_dark).show(getSupportFragmentManager(), (String) null);
                return true;
            case R.id.action_import /* 2131296364 */:
                importDefinitions();
                return true;
            default:
                return false;
        }
    }

    public void onLoadFinished(a.k.b.c<Boolean> cVar, Boolean bool) {
        a.k.a.a.a(this).a(99);
        new Handler().post(new Runnable() { // from class: no.nordicsemi.android.mcp.definitions.a
            @Override // java.lang.Runnable
            public final void run() {
                DefinitionsActivity.this.b();
            }
        });
        if (bool == null || Boolean.FALSE.equals(bool)) {
            Toast.makeText(this, R.string.error_import_failed, 0).show();
        }
    }
}
