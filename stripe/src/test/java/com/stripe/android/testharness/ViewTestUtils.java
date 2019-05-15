package com.stripe.android.testharness;


import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Utility class for common actions to perform on Views under test.
 */
public class ViewTestUtils {

    /**
     * Send an action down call on the delete key.
     *
     * @param editText the {@link EditText} to which to dispatch the key press.
     */
    public static void sendDeleteKeyEvent(@NonNull EditText editText) {
        editText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        editText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
    }

    /**
     * Utility function to check and see whether or not an EditText has a max length set, because
     * looping through this manually each time is way too much code.
     *
     * @param editText the {@link EditText} you'd like to test
     * @param max the max value that you're looking for
     * @return {@code true} if there is an {@link android.text.InputFilter.LengthFilter} on this
     * {@link EditText} has a "max" value equal to the input value.
     */
    public static boolean hasMaxLength(@NonNull EditText editText, @IntRange(from = 0) int max) {
        boolean foundLengthFilterOfCorrectSize = false;
        final InputFilter[] filters = editText.getFilters();
        if (filters == null) {
            return false;
        }

        for (InputFilter filter : filters) {
            if (filter instanceof InputFilter.LengthFilter) {
                foundLengthFilterOfCorrectSize =
                        ((InputFilter.LengthFilter) filter).getMax() == max;
                if (foundLengthFilterOfCorrectSize) {
                    break;
                }
            }
        }
        return foundLengthFilterOfCorrectSize;
    }
}
