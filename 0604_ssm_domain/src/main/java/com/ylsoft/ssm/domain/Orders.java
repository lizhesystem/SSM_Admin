package com.ylsoft.ssm.domain;

import com.ylsoft.ssm.utils.DateUtils;
import java.util.Date;
import java.util.List;

public class Orders {
    private String id;
    private String orderNum;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;
    // date转字符串
    private String orderTimeStr;

    // 支付状态 -- 0 未支付 1 已支付
    private Integer orderStatus;
    private String orderStatusStr;

    //出行人数
    private int peopleCount;

    // 订单表里关联的产品，一个订单只有一个产品
    private Product product;
    // 游客
    private List<Traveller> travellers;
    // 会员
    private Member member;

    // 支付方式 --0 支付宝 1 微信 2其它
    private int payType;
    // 支付状态
    private String payTypeStr;
    // 订单状态
    private String orderDesc;


    public String getOrderStatusStr() {
        if (orderStatus != null) {
            if (orderStatus == 0) {
                orderStatusStr = "未支付";
            } else if (orderStatus == 1) {
                orderStatusStr = "已支付";
            }
        }
        return orderStatusStr;
    }


    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {

        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime != null) {
            orderTimeStr = DateUtils.DateToString(orderTime, "yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", orderTime=" + orderTime +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", orderStatus=" + orderStatus +
                ", peopleCount=" + peopleCount +
                ", product=" + product +
                ", travellers=" + travellers +
                ", member=" + member +
                ", payType=" + payType +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                '}';
    }
}
