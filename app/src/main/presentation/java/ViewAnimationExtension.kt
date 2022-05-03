//import android.view.View
//import android.view.animation.Animation
//import android.view.animation.AnimationUtils
//import androidx.annotation.AnimRes
//
//
//private class AnimationListener(
//    private val onAnimationRepeat: () -> Unit,
//    private val onAnimationStart: () -> Unit,
//    private val onAnimationEnd: () -> Unit
//) : Animation.AnimationListener {
//    override fun onAnimationRepeat(p0: Animation?) = onAnimationRepeat()
//    override fun onAnimationStart(p0: Animation?) = onAnimationStart()
//    override fun onAnimationEnd(p0: Animation?) = onAnimationEnd()
//}
//
//fun View.playAnimation(
//    @AnimRes animResId: Int,
//    onAnimationRepeat: () -> Unit = {},
//    onAnimationStart: () -> Unit = {},
//    onAnimationEnd: () -> Unit = {}
//) = with(AnimationUtils.loadAnimation(context, animResId)) {
//    setAnimationListener(
//        AnimationListener(
//            onAnimationRepeat,
//            onAnimationStart,
//            onAnimationEnd
//        )
//    )
//    startAnimation(this)
//}
//
////fun View.flashAnimation(){
////    playAnimation(
////        animResId = R.anim.animation_flash
////    )
////}
//
//
//fun View.naxtPageAnimation1(view1: View, view2: View, view3: View) =
//    playAnimation(
//        animResId = R.anim.pager_anim,
//        onAnimationEnd = {
//            view1.playAnimation(
//                animResId = R.anim.pager_anim,
//                onAnimationEnd = {
//                    view1.visibility = View.VISIBLE
//                    view2.playAnimation(
//                        animResId = R.anim.pager_anim,
//                        onAnimationEnd = {
//                            view2.visibility = View.VISIBLE
//                            view3.playAnimation(
//                                animResId = R.anim.pager_anim,
//                                onAnimationEnd = {
//                                    view3.visibility = View.VISIBLE
//                                }
//                            )
//                        }
//                    )
//                }
//            )
//        }
//    )
//
//fun View.naxtPageAnimation1(view1: View, view2: View) =
//    playAnimation(
//        animResId = R.anim.pager_anim,
//        onAnimationEnd = {
//            view1.playAnimation(
//                animResId = R.anim.pager_anim,
//                onAnimationEnd = {
//                    view1.visibility = View.VISIBLE
//                    view2.playAnimation(
//                        animResId = R.anim.pager_anim,
//                        onAnimationEnd = {
//                            view2.visibility = View.VISIBLE
//                        }
//                    )
//                }
//            )
//        }
//    )
//
//fun View.naxtPageAnimation1(view1: View) =
//    playAnimation(
//        animResId = R.anim.pager_anim,
//        onAnimationEnd = {
//            view1.playAnimation(
//                animResId = R.anim.pager_anim,
//                onAnimationEnd = {
//                    view1.visibility = View.VISIBLE
//                }
//            )
//        }
//    )
//
//fun View.naxtPageAnimation1() =
//    playAnimation(
//        animResId = R.anim.pager_anim,
//        onAnimationEnd = {
//        }
//    )
//
//
//fun View.naxtPageAnimation2() =
//    playAnimation(
//        animResId = R.anim.pager_anim_delay2,
//        onAnimationEnd = {
//            this.playAnimation(
//                animResId = R.anim.pager_anim,
//
//            )
//        }
//    )
//
//fun View.naxtPageAnimation3() =
//    playAnimation(
//        animResId = R.anim.pager_anim_delay3,
//        onAnimationEnd = {
//            this.playAnimation(
//                animResId = R.anim.pager_anim,
//            )
//        }
//    )
//
//fun View.naxtPageAnimation4() =
//    playAnimation(
//        animResId = R.anim.pager_anim_delay4,
//        onAnimationEnd = {
//            this.playAnimation(
//                animResId = R.anim.pager_anim,
//            )
//        }
//    )
//
//
////Toast
//
//fun View.doToast() =
//    playAnimation(
//        animResId = R.anim.toast_anim,
//        onAnimationEnd = {
//            this.playAnimation(
//                animResId = R.anim.delay1500ms_anim,
//                onAnimationEnd = {
//                    this.playAnimation(
//                        animResId = R.anim.fadout_anim,
//                        onAnimationEnd = {
//                            this.visibility = View.INVISIBLE
//                        }
//                    )
//                }
//            )
//        }
//    )