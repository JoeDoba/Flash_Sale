package joe.doba.seckill_demo1.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private String orderNo;

    private Integer orderStatus;

    private Long seckillActivityId;

    private Long userId;

    private BigDecimal orderAmount;

    private Date createTime;

    private Date payTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getSeckillActivityId() {
        return seckillActivityId;
    }

    public void setSeckillActivityId(Long seckillActivityId) {
        this.seckillActivityId = seckillActivityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}