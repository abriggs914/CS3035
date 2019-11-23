
CS 3503 Assignment 4

	Nov.22/19
	Avery Briggs
	3471065
	
	“JUSTIFICATION FOR TOOLBAR CODE PLACEMENT”
	
	I have chosen to put my toolbar for part 1 in the view of my application. First I instantiate a ToolBar class which extends
	pane, then I return a constructed ToolBar pane to the view class to be added to it's children. The toolbar has it's own controller
	which is instantiated in the main class at startup. I believe that this is a fair separation, since I still use the model class to
	keep track of all information that is accessible or modifiable by the toolbar or it's controller, like currShape or cutShapesList.
