package com.xilaida.mvvmlibrary.app

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.xilaida.mvvmlibrary.R
import com.xilaida.mvvmlibrary.common.Constants
import com.xilaida.mvvmlibrary.data.api.NetworkConnectionInterceptor
import com.xilaida.mvvmlibrary.data.db.AppDatabase
import com.xilaida.mvvmlibrary.data.repository.UserRepository
import com.xilaida.mvvmlibrary.utils.SPUtil
import com.xilaida.mvvmlibrary.viewmodel.AuthViewFactory
import com.xilaida.mvvmlibrary.viewmodel.RegisterModelFactory
import com.xilaida.mvvmlibrary.viewmodel.ViewModelFactory
import com.xilaida.mvvmlibrary.widgets.smartrefreshlayout.UniClassicsFooter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
@author cjq
@Date 6/9/2020
@Time 11:56 AM
@Describe:
 */
class App : MultiDexApplication(),KodeinAware {

    init {
        //设置全局的Header构建器
        //SmartRefreshLayout 初始化
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context).apply {
                setShowBezierWave(false)
                setColorSchemeResources(R.color.common_blue)
//                setBackgroundColor(resources.getColor(R.color.common_white))
            }
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            UniClassicsFooter(context).setDrawableSize(20f)
        }


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()
//        ToastUtils.init(this)
//        ARouter.openLog()
//        ARouter.openDebug()
//        ARouter.init(this)

        context = this

        //设置为日间模式
        if (SPUtil.getBoolean(Constants.ISNIGHT)){
            SPUtil.putBoolean(Constants.ISNIGHT, true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            SPUtil.putBoolean(Constants.ISNIGHT, false)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


    }


    companion object {
        lateinit var context: Context
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { ViewModelFactory(instance()) }
        bind() from provider { AuthViewFactory(instance()) }
        bind() from provider { RegisterModelFactory(instance()) }
    }
}