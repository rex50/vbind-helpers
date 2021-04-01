package mws.photography.media.mediaviewer.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import mws.photography.media.mediaviewer.BuildConfig;

public abstract class FragBase<Binding, Listener> extends Fragment {

    public Listener listener;

    public Binding binding;

    private boolean isFragmentLoaded;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bindView(inflater, container);
        return ((ViewBinding) binding).getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isFragmentLoaded) {
            isFragmentLoaded = true;
            load();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(isListenerType(context)) {
            listener = (Listener) context;
        } else if(BuildConfig.DEBUG) {
            throw new IllegalStateException(context.getPackageName() + " should implement required interfaces");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    protected boolean isListenerNotNull() {
        return listener != null;
    }

    /**
     * To bind the view
     *
     * Example: return LayoutNameBinding.inflate(inflater,container,false)
     *
     * @return the binding
     */
    protected abstract Binding bindView(LayoutInflater inflater, ViewGroup container);

    /**
     * used to verify that listener is implemented or not
     * @return true if context is of ListenerType else false
     */
    protected abstract boolean isListenerType(Context context);

    protected abstract void load();

}
