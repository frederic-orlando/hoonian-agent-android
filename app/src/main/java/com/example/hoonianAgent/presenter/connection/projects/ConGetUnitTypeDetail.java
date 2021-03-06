package com.example.hoonianAgent.presenter.connection.projects;

import android.content.Context;

import com.example.hoonianAgent.model.request.project.RequestGetUnitTypeDetail;
import com.example.hoonianAgent.model.response.project.ResponseGetUnitTypeDetail;
import com.example.hoonianAgent.presenter.connection.URL;

import java.util.Objects;

import connection.rxconnection.connection.HttpMethod;
import connection.rxconnection.connection.HttpRequest;

public class ConGetUnitTypeDetail extends HttpRequest<Objects, ResponseGetUnitTypeDetail> {
    public ConGetUnitTypeDetail(RequestGetUnitTypeDetail request, Context context) {
        super(context, ResponseGetUnitTypeDetail.class, URL.getUnitTypeDetail(request), HttpMethod.GET);
    }
}
