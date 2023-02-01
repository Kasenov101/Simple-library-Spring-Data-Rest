package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.OrderEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.WarehouseEntity;
import com.kasenov.libpro.simplelibrary.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    BookService bookService;
    @InjectMocks
    OrderService orderService;
    @Mock
    OrderRepository orderRepository;

    OrderEntity setupOrder() {
        OrderEntity order = new OrderEntity();
        order.setId(12);
        BookEntity book = new BookEntity();
        book.setId(12);
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setBookEntity(book);
        book.setWarehouseEntity(warehouse);
        order.setBookEntities(List.of(book));
        order.setReturnDate(LocalDate.now().plusDays(2));
        return order;
    }

    @Test
    void save_WhenReturnDateBeforeDateOfReceiving_ShouldThrowException() {
        OrderEntity order = setupOrder();
        order.setReturnDate(LocalDate.now().minusDays(1));

        assertThatThrownBy(() -> orderService.save(order)).isInstanceOf(CannotSaveException.class);
    }

    @Test
    void save_WhenReturnDateMoreThanOneMonth_ShouldThrowException() {
        OrderEntity order = setupOrder();
        order.setReturnDate(LocalDate.now().plusDays(31));

        assertThatThrownBy(() -> orderService.save(order)).isInstanceOf(CannotSaveException.class);
    }

    @Test
    void save_WhenOrderSave_ShouldQuantityInWarehouseDecrease() throws CannotSaveException,
                                                                        NotFoundException {
        OrderEntity order = setupOrder();
        BookEntity book = order.getBookEntities().get(0);
        book.getWarehouseEntity().setQuantity(2);
        when(bookService.getByIdWhereQuantityAboveZero(anyLong())).thenReturn(book);
        when(orderRepository.saveAndFlush(order)).thenReturn(order);

        BookEntity book1 = orderService.save(order).getBookEntities().get(0);

        assertThat(book1.getWarehouseEntity().getQuantity()).isEqualTo(1);
    }

    @Test
    void save_WhenOrderSave_ShouldQuantityAtClientIncrease() throws CannotSaveException, NotFoundException {
        OrderEntity order = setupOrder();
        BookEntity book = order.getBookEntities().get(0);
        book.getWarehouseEntity().setAtClients(0);
        when(bookService.getByIdWhereQuantityAboveZero(anyLong())).thenReturn(book);
        when(orderRepository.saveAndFlush(order)).thenReturn(order);

        BookEntity book1 = orderService.save(order).getBookEntities().get(0);

        assertThat(book1.getWarehouseEntity().getAtClients()).isEqualTo(1);
    }

    @Test
    void removeById_WithOrderIsCompletedFalse_ShouldThrowException() {
        OrderEntity order = setupOrder();
        order.setOrderIsCompleted(false);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> orderService.removeById(anyLong()))
                .isInstanceOf(CannotRemoveException.class);
    }

    @Test
    void removeById_WhenOrderIsNotFound_ShouldThrowException() {
        Optional<OrderEntity> optional = Optional.empty();
        when(orderRepository.findById(anyLong())).thenReturn(optional);

        assertThatThrownBy(() -> orderService.removeById(anyLong()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void completeTheOrder_WhenOrderHasAlreadyBeenCompleted_ShouldThrowException() {
        OrderEntity order = setupOrder();
        order.setOrderIsCompleted(true);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        assertThatThrownBy(()-> orderService.completeTheOrder(anyLong()))
                .isInstanceOf(CannotRemoveException.class);
    }

    @Test
    void completeTheOrder_WhenReturnDateEqualsReturned_ShouldMarkReturnedOnTimeTrue() throws CannotRemoveException,
                                                                                            NotFoundException {
        OrderEntity order = setupOrder();
        order.setReturnedOnTime(false);
        order.setReturnDate(LocalDate.now());
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        orderService.completeTheOrder(anyLong());

        assertThat(order.isReturnedOnTime()).isTrue();
    }

    @Test
    void completeTheOrder_WhenReturnedOnTime_ShouldMarkReturnedOnTimeTrue() throws CannotRemoveException,
                                                                                        NotFoundException {
        OrderEntity order = setupOrder();
        order.setReturnedOnTime(false);
        order.setReturnDate(LocalDate.now().plusWeeks(2));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        orderService.completeTheOrder(anyLong());

        assertThat(order.isReturnedOnTime()).isTrue();
    }

    @Test
    void completeTheOrder_WhenTimeHasPassed_ShouldMarkReturnedOnTimeFalse() throws CannotRemoveException,
                                                                                    NotFoundException {
        OrderEntity order = setupOrder();
        order.setReturnedOnTime(true);
        order.setReturnDate(LocalDate.now().minusDays(1));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        orderService.completeTheOrder(anyLong());

        assertThat(order.isReturnedOnTime()).isFalse();
    }

    @Test
    void completeTheOrder_AfterExecution_ShouldSetReturnedDate() throws CannotRemoveException, NotFoundException {
        OrderEntity order = setupOrder();
        order.setOrderIsCompleted(false);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        orderService.completeTheOrder(anyLong());

        assertThat(LocalDate.now()).isEqualTo(order.getReturnedDate());
    }

    @Test
    void completeTheOrder_AfterCompletion_ShouldQuantityWarehouseIncrease() throws CannotRemoveException,
                                                                                    NotFoundException {
        OrderEntity order = setupOrder();
        WarehouseEntity warehouse = order.getBookEntities().get(0).getWarehouseEntity();
        warehouse.setQuantity(0);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        orderService.completeTheOrder(anyLong());

        assertThat(warehouse.getQuantity()).isEqualTo(1);
    }

    @Test
    void completeTheOrder_AfterCompletion_ShouldQuantityAtClientDecrease() throws CannotRemoveException,
                                                                                    NotFoundException {
        OrderEntity order = setupOrder();
        WarehouseEntity warehouse = order.getBookEntities().get(0).getWarehouseEntity();
        warehouse.setAtClients(1);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        orderService.completeTheOrder(anyLong());

        assertThat(warehouse.getAtClients()).isEqualTo(0);
    }
}