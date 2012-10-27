package com.starbug1.android.wastednews;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.starbug1.android.newsapp.FetchFeedService;
import com.starbug1.android.newsapp.data.NewsListItem;

public class AppFetchFeedService extends FetchFeedService {

	private final Pattern imageUrl_ = Pattern.compile(
			"<img.*?src=\"([^\"]*)\"", Pattern.MULTILINE);
	private final Pattern imageOriginalUrl_ = Pattern.compile(
			"<img.*?data-original=\"([^\"]*)\"", Pattern.MULTILINE);
	private final Pattern newsliteContent_ = Pattern.compile(
			"<div class=\"asset-content\">(.*)<div class=\"asset-footer\">",
			Pattern.DOTALL);

	@Override
	protected List<Feed> getFeeds() {
		List<Feed> feeds = new ArrayList<Feed>();
		feeds.add(new Feed("cracked", "http://feeds.feedburner.com/CrackedRSS",
				"feeds.feedburner.com") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				return null;
			}

		});
		feeds.add(new Feed("theawl", "http://feeds2.feedburner.com/TheAwl",
				"feeds.feedburner.com") {
			@Override
			public String getImageUrl(String content, NewsListItem item) {
				return null;
			}

		});
		feeds.add(new Feed("lefthandedtoons",
				"http://feeds.feedburner.com/lefthandedtoons/awesome",
				"feeds.feedburner.com") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				return null;
			}

		});
		feeds.add(new Feed("newslite.tv", "http://newslite.tv/rss.xml") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				Matcher m = newsliteContent_.matcher(content);
				if (!m.find()) {
					return null;
				}
				String mainPart = m.group(1);
				m = imageUrl_.matcher(mainPart);
				if (!m.find()) {
					return null;
				}
				return m.group(1);
			}

		});

		return feeds;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}
}
