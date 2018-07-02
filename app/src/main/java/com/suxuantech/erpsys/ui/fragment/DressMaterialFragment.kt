package com.suxuantech.erpsys.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.DressEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.adapter.ExpandableAdapter
import com.suxuantech.erpsys.ui.widget.ErrorView
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response

class DressMaterialFragment : BaseSupportFragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mListener: OnFragmentInteractionListener? = null
    var errorView: ErrorView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    /**
     * 获取礼服
     */
    private fun getPackage() {
        val Url = Contact.getFullUrl(Contact.CUSTOMER_DRESS, Contact.TOKEN, arguments?.get("orderId"), App.getApplication().userInfor.shop_code);
        //请求实体
        val districtBeanJavaBeanRequest = JavaBeanRequest(Url, RequestMethod.POST, DressEntity::class.java)
        val searchByCustmor = object : HttpListener<DressEntity> {
            override fun onSucceed(what: Int, response: Response<DressEntity>) {
                errorView?.reset()
                if (response.get().isOK) {
                    val data = response.get().data
                    setData(data)
                } else {
                    errorView?.reset()
                    errorView?.let { smartRefreshLayout?.setRefreshContent(it) }
                    smartRefreshLayout?.finishRefresh(true)
                }
            }

            override fun onFailed(what: Int, response: Response<DressEntity>) {
                errorView?.reset()
                errorView?.let { smartRefreshLayout?.setRefreshContent(it) }
                smartRefreshLayout?.finishRefresh(false)
            }
        }
        request(2, districtBeanJavaBeanRequest, searchByCustmor, false, false)
    }

    fun setData(data: MutableList<DressEntity.DataBean>) {
        adapter?.upData(data)
        smartRefreshLayout?.finishRefresh()
        smartRefreshLayout?.setRefreshContent(this?.recy!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        errorView = ErrorView(context);
        return inflater.inflate(R.layout.smartrefresh_and_recyclerview, container, false)
    }

    var recy: RecyclerView? = null;
    var smartRefreshLayout: SmartRefreshLayout? = null;
    var adapter: ExpandableAdapter? = null;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recy = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = ExpandableAdapter(activity, null)
        adapter?.setOnHeaderClickListener { adapter, holder, groupPosition ->
            val expandableAdapter = adapter as ExpandableAdapter
            if (expandableAdapter.isExpand(groupPosition)) {
                expandableAdapter.collapseGroup(groupPosition)
            } else {
                expandableAdapter.expandGroup(groupPosition)
            }
        }
        recy?.adapter = adapter
        smartRefreshLayout = view.findViewById<SmartRefreshLayout>(R.id.srl_fresh)
        smartRefreshLayout?.setEnableLoadMore(false);
        smartRefreshLayout?.setRefreshContent(this?.errorView!!)
        errorView?.setOnClickListener(View.OnClickListener {
            errorView?.setProgressBarVisible(true)
            smartRefreshLayout?.autoRefresh()
        })
        smartRefreshLayout?.setOnRefreshListener { getPackage() }
        recy?.layoutManager = LinearLayoutManager(activity);
        smartRefreshLayout?.autoRefresh()

        super.onViewCreated(view, savedInstanceState)
    }


    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DressMaterialFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): DressMaterialFragment {
            val fragment = DressMaterialFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
