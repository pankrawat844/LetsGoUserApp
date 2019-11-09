package come.texi.driver.utils

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.method.Touch.onTouchEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


open class RecyclerItemClickListener(context: Context, private val mListener: OnItemClickListener?) :
    RecyclerView.OnItemTouchListener {
    internal var mGestureDetector: GestureDetector

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    init {
        mGestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.getX(), e.getY())
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}