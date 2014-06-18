/* Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb

import geb.content.NavigableSupport
import geb.content.PageContentTemplate
import geb.content.PageContentTemplateBuilder
import geb.content.TemplateDerivedPageContent
import geb.download.DownloadSupport
import geb.frame.FrameSupport
import geb.js.AlertAndConfirmSupport
import geb.js.JavascriptInterface
import geb.navigator.Navigator
import geb.textmatching.TextMatchingSupport
import geb.waiting.WaitingSupport

class Module extends TemplateDerivedPageContent {

	static base = null
	
	@Delegate private NavigableSupport navigableSupport
	@Delegate private DownloadSupport _downloadSupport
	@Delegate private WaitingSupport _waitingSupport
	@Delegate private FrameSupport frameSupport
	
	@Delegate private TextMatchingSupport textMatchingSupport = new TextMatchingSupport()
	@Delegate private AlertAndConfirmSupport _alertAndConfirmSupport
	
	void init(PageContentTemplate template, Navigator navigator, Object[] args) {
		Map<String, PageContentTemplate> contentTemplates = PageContentTemplateBuilder.build(template.config, this, 'content', this.class, Module)
		navigableSupport = new NavigableSupport(this, contentTemplates, navigator.browser.navigatorFactory.relativeTo(navigator))
		super.init(template, navigator, *args)
		_downloadSupport = new DownloadSupport(browser)
		_waitingSupport  = new WaitingSupport(browser.config)
		frameSupport = new FrameSupport(browser)
		_alertAndConfirmSupport = new AlertAndConfirmSupport({ this.getJs() }, browser.config)
	}

	JavascriptInterface getJs() {
		page.js
	}

}