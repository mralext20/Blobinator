package net.yosi.isageek.blobinator;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import net.yosi.isageek.blobinator.Game;

public class MainActivity extends AndroidApplication
{
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        initialize(new Game(), cfg);
    }
}
