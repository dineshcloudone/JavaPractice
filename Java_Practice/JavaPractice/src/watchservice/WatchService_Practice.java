package watchservice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchService_Practice {

	public static void main(String[] args) throws IOException {

		WatchService ws = FileSystems.getDefault().newWatchService();

		Path directory = Paths.get("C:\\Dinesh");

		WatchKey watchKey = directory.register(ws, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
		
		while(true) {
			for(WatchEvent<?> event:watchKey.pollEvents()) {
				System.out.println(event.kind());
				Path file=directory.resolve((Path)event.context());
				System.out.println(file+" was last modified at " + file.toFile().lastModified());
			}
		}
	}

}
