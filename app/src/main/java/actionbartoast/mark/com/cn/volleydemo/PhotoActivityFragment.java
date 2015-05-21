package actionbartoast.mark.com.cn.volleydemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class PhotoActivityFragment extends Fragment {

    public PhotoActivityFragment() {
    }
    private GridView gvCar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_image_request, container,false);

        gvCar = (GridView) view.findViewById(R.id.gv_car);
        ImageBaseAdapter adapter = new ImageBaseAdapter(getActivity(),Constants.IMAGE_URLS);
        gvCar.setAdapter(adapter);
        return view;
    }
}
