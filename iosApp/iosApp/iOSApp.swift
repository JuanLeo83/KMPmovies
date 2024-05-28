import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoinHelperKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
// 			ContentView()
            ContentView(viewModel: ContentView.ViewModel())
		}
	}
}