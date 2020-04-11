package com.example.android.databinding.basicsample.util

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

/**
 * A ViewModel that is also an Observable,
 * to be used with the Data Binding Library.
 *
 *
 * There are situations where you might prefer to use a ViewModel component that implements the Observable interface over using LiveData objects,
 * even if you lose the lifecycle management capabilities of LiveData. Using a ViewModel component that implements Observable gives you more control
 * over the binding adapters in your app. For example, this pattern gives you more control over the notifications when data changes, it also allows
 * you to specify a custom method to set the value of an attribute in two-way data binding.
 *
 * To implement an observable ViewModel component, you must create a class that inherits from the ViewModel class and implements the Observable
 * interface. You can provide your custom logic when an observer subscribes or unsubscribes to notifications using the addOnPropertyChangedCallback()
 * and removeOnPropertyChangedCallback() methods. You can also provide custom logic that runs when properties change in the notifyPropertyChanged() method.
 *
 */
open class ObservableViewModel : ViewModel(), Observable {
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}