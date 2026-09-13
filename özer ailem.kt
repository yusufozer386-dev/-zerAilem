package com.example

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.Qualities

class YouTubeProvider : MainAPI() {
    override var mainUrl = "https://www.youtube.com"
    override var name = "YouTube Stream"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override var lang = "tr"
    override val hasMainPage = true

    // Ana sayfada veya arama sonuçlarında gösterilecek içerik yükleme
    override async fun load(url: String): LoadResponse? {
        val title = "Örnek YouTube Videosu"
        val poster = "https://img.youtube.com/vi/z3NjaaxeVtY/hqdefault.jpg"
        
        return MovieLoadResponse(
            name = title,
            url = url,
            apiName = this.name,
            type = TvType.Movie,
            dataUrl = url,
            posterUrl = poster
        )
    }

    // Video oynatıcıya medya linkini iletme
    override async fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        // Doğrudan veya extractor vasıtasıyla video akış adresi sağlanır
        callback(
            ExtractorLink(
                source = this.name,
                name = "YouTube HD Stream",
                url = data, // Doğrudan veya çözümlenmiş stream adresi
                referer = mainUrl,
                quality = Qualities.Unknown.value,
                isM3u8 = false
            )
        )
        return true
    }
}