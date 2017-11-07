package store.tornado.alex.unittesting;

/**
 * Created by: anna
 * Date: 11/5/17.
 */

public class MainPresenter implements MVP.Presenter {
	private final MVP.Model model;
	private final MVP.View view;

	public MainPresenter(MVP.Model model, MVP.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void onGenerateClicked(String prefix) {
		this.model.generate(prefix);
	}

	@Override
	public void onViewCreated() {
		view.updateResult("please enter prefix");
	}
}
