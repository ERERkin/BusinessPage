package kg.ItAcademy.BusinessPage.service.Impl;

import kg.ItAcademy.BusinessPage.entity.Log;
import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.model.PopularPageModel;
import kg.ItAcademy.BusinessPage.repos.LogRepo;
import kg.ItAcademy.BusinessPage.service.LogService;
import kg.ItAcademy.BusinessPage.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.sort;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogRepo logRepo;
    @Autowired
    PageService pageService;

    @Override
    public Log getById(Long id) {
        return null;
    }

    @Override
    public List<Log> getAll() {
        return null;
    }

    @Override
    public Log save(Log item) {
        return logRepo.save(item);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void logAdd(Date date, Page page) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            today = sdf.parse(sdf.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("HIII");
//        System.out.println(logRepo.getPopular());
        Long l = logRepo.isViewThisDate(today, page);
        if(l == 0){
            Log log = Log.builder()
                    .page(page)
                    .date(today)
                    .views(1L)
                    .build();
            logRepo.save(log);
        }else if(l > 0){
            logRepo.updateThisDate(today, page);
        }
    }

    @Override
    public List<PopularPageModel> getTop() {
        List<PopularPageModel> list = new ArrayList<>();
        List<Object> objects = (List<Object>)logRepo.getPopular();
        for(Object ob : objects){
            Object[] arr = (Object[]) ob;
            Page page = pageService.getById((Long) arr[0]);
            PopularPageModel ppm = PopularPageModel.builder()
                    .name(page.getName())
                    .address(page.getAddress())
                    .count((Long)arr[1])
                    .build();
            list.add(ppm);
        }
        sort(list);
        Stream<PopularPageModel> stream = list.stream();
        list = stream.filter(m -> m.getCount() > 0).limit(5).collect(Collectors.toList());
        System.out.println(list);
        return list;
    }
}
