package com.stripe.android.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.stripe.android.model.PaymentIntent;

/**
 * A class that manages starting a {@link PaymentAuthWebViewActivity} instance with the correct
 * arguments.
 */
public class PaymentAuthWebViewStarter {
    static final String EXTRA_AUTH_URL = "auth_url";
    static final String EXTRA_RETURN_URL = "return_url";

    private static final int REQUEST_CODE = 50000;

    @NonNull private final Activity mActivity;

    PaymentAuthWebViewStarter(@NonNull Activity activity) {
        mActivity = activity;
    }

    /**
     * @param redirectData typically obtained through {@link PaymentIntent#getRedirectData()}
     *
     */
    public void start(@NonNull PaymentIntent.RedirectData redirectData) {
        final Intent intent = new Intent(mActivity, PaymentAuthWebViewActivity.class)
                .putExtra(EXTRA_AUTH_URL, redirectData.url.toString())
                .putExtra(EXTRA_RETURN_URL, redirectData.returnUrl != null ?
                        redirectData.returnUrl.toString() : null);
        mActivity.startActivityForResult(intent, REQUEST_CODE);
    }

    static boolean isAuthWebViewResult(int requestCode) {
        return requestCode == REQUEST_CODE;
    }
}
