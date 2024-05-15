import android.content.Context
import androidx.lifecycle.ViewModel
import com.ehsieh2.memez.R

class MemeViewModel : ViewModel() {
    private val imageIds = arrayOf(
        R.drawable.cat,
        R.drawable.confused_guy,
        R.drawable.girl_and_fire,
        R.drawable.megamind,
        R.drawable.mike,
        R.drawable.shrek,
        R.drawable.simpson,
        R.drawable.sunglasses,
    )

    private val memeQuotes = arrayOf(
        R.string.quote_01,
        R.string.quote_02,
        R.string.quote_03,
        R.string.quote_04,
        R.string.quote_05,
        R.string.quote_06,
        R.string.quote_07,
        R.string.quote_08,
    )

    fun generateRandomMemeQuote(context: Context): String {
        val randomMemeQuoteId = memeQuotes.random()
        return context.getString(randomMemeQuoteId)
    }

    fun generateRandomMemeImage(context: Context): Int {
        val randomImageId = imageIds.random()
        val randomImageResource = randomImageId

        return randomImageResource
    }
}
