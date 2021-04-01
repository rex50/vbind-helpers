package mws.photography.media.mediaviewer.base;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class ActBase<T> extends AppCompatActivity {

    public T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = bindView();

        setContentView(((ViewBinding) binding).getRoot());

        init();
    }

    /**
     * To bind the layout to activity.
     *
     * Example: LayoutNameBinding.inflate(getLayoutInflater());
     */
    @NonNull
    public abstract T bindView();

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
