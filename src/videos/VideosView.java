package videos;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Video;
import root.RootModel;
import utils.CategoryView;
import utils.MediaLibrary;

import java.io.IOException;

public class VideosView extends TableView<Video> implements CategoryView {
	private VideosController videosController;
	private TableColumn<Video, String> titleCol;
	private TableColumn<Video, String> durationCol;
	private RootModel rootModel;

	public VideosView(final VideosController controller) {
		videosController = controller;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("videos.fxml"));
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		lookupViews();

		setItems(MediaLibrary.instance().getVideos());

		setPlaceholder(new Label("Choose a Directory to play videos"));
		titleCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getTitle()));
		durationCol.setCellValueFactory(p -> p.getValue().durationProperty());

		// Subtract 20 total to leave room for the scrollbar
		titleCol.prefWidthProperty().bind(widthProperty().multiply(0.7).subtract(10));
		durationCol.prefWidthProperty().bind(widthProperty().multiply(0.3).subtract(10));

		getSelectionModel().selectedItemProperty().addListener(videosController.selectionListener(getItems()));
	}

	@SuppressWarnings("unchecked")
	private void lookupViews() {
		titleCol = (TableColumn<Video, String>) getVisibleLeafColumn(0);
		durationCol = (TableColumn<Video, String>) getVisibleLeafColumn(1);
	}

	@Override
	public void setListeners(final RootModel rootModel) {
		rootModel.setPlaylistModeListener(newValue -> {

        });
		rootModel.setSearchListener(searchText -> {
			getSelectionModel().clearSelection();
			setItems(MediaLibrary.instance().getVideos().filtered(videosController.searchFilter(searchText)));
		});
	}
}
