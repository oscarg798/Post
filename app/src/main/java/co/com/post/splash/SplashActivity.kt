package co.com.post.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.com.post.BaseApplication
import co.com.post.main.MainActivity
import co.com.post.R

class SplashActivity : AppCompatActivity(), ISplashActivityView {

    private lateinit var mPresenter: ISplashactivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val presenter = SplashActivityPresenter()
        (applicationContext as BaseApplication).getAppComponent().inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)
    }

    override fun initComponents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}
