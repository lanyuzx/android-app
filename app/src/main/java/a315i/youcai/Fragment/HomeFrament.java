package a315i.youcai.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.xutils.http.RequestParams;
import java.util.List;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class HomeFrament extends MainFragment {
    private RecyclerView mRecyclerView;
    @Override
    public void setupData() {
        RequestParams params = new RequestParams("https://api.youcai.xin/app/home");

        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                LogTools.i(response);
               HomeModel model =   GsonTools.parseJsonWithGson(response, HomeModel.class);
                List<HomeModel.HomeChildModel> items = model.items;
                List<HomeModel.HomeChildModel> tops = model.tops;
                List<HomeModel.HomeChildModel> entries = model.entries;
                List<HomeModel.slidesModel> slidesModels = model.slides;
                ToastTools.showLong(getActivity(),"请求成功");
            }
            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showLong(getActivity(),error.getMessage());

            }
        });

    }

    @Override
    public View setupView() {
        View homeView = View.inflate(getActivity(), R.layout.fragment_home,null);
        mRecyclerView = (RecyclerView) homeView.findViewById(R.id.homeRecyclerView);
        return homeView;
    }
}
