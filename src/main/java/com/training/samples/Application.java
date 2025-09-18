package com.training.samples;

import com.webforj.App;
import com.webforj.annotation.AppEntry;
import com.webforj.annotation.Routify;
import com.webforj.annotation.AppTitle;
import com.webforj.annotation.StyleSheet;
import com.webforj.annotation.AppTheme;

@AppEntry
@AppTitle("Training")
@Routify(packages = "com.training.samples.views") 
@StyleSheet("ws://app.css")
@AppTheme("system")
public class Application extends App { }
