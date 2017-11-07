package store.tornado.alex.unittesting;

import android.text.Editable;

/**
 * Created by: anna
 * Date: 11/5/17.
 */

public interface MVP {
	interface View {
		void updateResult(String result);
	}

	interface Presenter {

		void onGenerateClicked(String prefix);

		void onViewCreated();
	}

	interface Model {

		void generate(String prefix);
	}
}
