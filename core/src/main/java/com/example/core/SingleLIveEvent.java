package com.example.core;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLIveEvent<T> extends MutableLiveData<T> {

    private  final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner , t -> {
            if (mPending.compareAndSet(true, false)){
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(T t) {
        mPending.set(true);
        super.setValue(t);
    }
    public void call(){
        setValue(null);
    }

}
