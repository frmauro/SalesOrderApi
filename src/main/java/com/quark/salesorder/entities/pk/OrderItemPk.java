package com.quark.salesorder.entities.pk;

import java.io.Serializable;

import com.quark.salesorder.entities.Order;

public class OrderItemPk implements Serializable {
    private static final long serialVersionUID = 1L;

    private Order order;

    public Order getOrder() {
		return order;
	}

    public void setOrder(Order order) {
		this.order = order;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItemPk other = (OrderItemPk) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        return true;
    }
    


    
}
