drop database if exists TiendaB2_IN5CM;
create database TiendaB2_IN5CM;
use TiendaB2_IN5CM;

create table Usuarios(
	codigo_usuario int primary key not null auto_increment,
    username varchar (45),
    password varchar (45),
    email varchar (60),
    rol  varchar (45),
    estado int
);

create table Clientes(
	dpi_cliente int primary key not null auto_increment,
    nombre_cliente varchar (50),
    apellido_cliente varchar (50),
    direccion varchar (100),
    estado int
);

create table Productos(
	codigo_producto int primary key not null auto_increment,
    nombre_producto varchar(60),
    precio decimal,
    stock int,
    estado int
    
);

create table Ventas(
	codigo_venta int primary key not null auto_increment,
    fecha_venta date,
    total decimal (10,2),
    estado int,
    clientes_dpi_cliente int,
    usuarios_codigo_usuario int,
    foreign key(clientes_dpi_cliente) references Clientes(dpi_cliente) on delete cascade,
    foreign key(usuarios_codigo_usuario) references Usuarios(codigo_usuario) on delete cascade

);

create table Detalle_Venta(
	codigo_detalle_venta int primary key not null auto_increment,
    cantidad int,
    precio_unitario decimal(10,2),
    subtotal decimal (10,2),
    productos_codigo_producto int,
    ventas_codigo_venta int,
    foreign key(productos_codigo_producto) references Productos(codigo_producto) on delete cascade,
    foreign key(ventas_codigo_venta) references Ventas(codigo_venta) on delete cascade

);

-- ============== Usuarios ============= --

delimiter $$
create procedure sp_AgregarUsuario(
    in p_username varchar(45),
    in p_password varchar(45),
    in p_email varchar(60),
    in p_rol varchar(45),
    in p_estado int

)
begin
	insert into Usuarios(username, password, email, rol, estado)
    values(p_username, p_password, p_email, p_rol, p_estado);

end $$
delimiter ;

call sp_AgregarUsuario('admin1','1234','admin1@mail.com','admin',1);
call sp_AgregarUsuario('user1','1234','user1@mail.com','vendedor',1);
call sp_AgregarUsuario('user2','1234','user2@mail.com','vendedor',1);
call sp_AgregarUsuario('user3','1234','user3@mail.com','vendedor',1);
call sp_AgregarUsuario('user4','1234','user4@mail.com','vendedor',1);
call sp_AgregarUsuario('user5','1234','user5@mail.com','vendedor',1);
call sp_AgregarUsuario('user6','1234','user6@mail.com','vendedor',1);
call sp_AgregarUsuario('user7','1234','user7@mail.com','vendedor',1);
call sp_AgregarUsuario('user8','1234','user8@mail.com','vendedor',1);
call sp_AgregarUsuario('user9','1234','user9@mail.com','vendedor',1);


delimiter $$
create procedure sp_EditarUsuario(
	in p_codigo_usuario int,
    in p_username varchar(45),
    in p_password varchar(45),
    in p_email varchar(60),
    in p_rol varchar(45),
    in p_estado int

)
begin
	update Usuarios set username = p_username, password = p_password, email = p_email, rol = p_rol, estado = p_estado
    where codigo_usuario = p_codigo_usuario;

end $$
delimiter ;

delimiter $$
create procedure sp_ListarUsuarios()
begin
	select * from Usuarios order by codigo_usuario;
end $$
delimiter ;

delimiter $$
create procedure sp_EliminarUsuario(
	in p_codigo_usuario int
)
begin
	delete from Usuarios where codigo_usuario = p_codigo_usuario;
end $$
delimiter ;

-- =========== CLientes =========== -- 

delimiter $$
create procedure sp_AgregarCliente(
	in p_nombre_cliente varchar(50),
    in p_apellido_cliente varchar(50),
    in p_direccion varchar (100),
    in p_estado int
)
begin 
	insert into Clientes(nombre_cliente, apellido_cliente, direccion, estado)
    values( p_nombre_cliente, p_apellido_cliente, p_direccion, p_estado);
end $$
delimiter ;
call sp_AgregarCliente('Juan','Perez','Zona 1',1);
call sp_AgregarCliente('Maria','Lopez','Zona 2',1);
call sp_AgregarCliente('Carlos','Ramirez','Zona 3',1);
call sp_AgregarCliente('Ana','Gomez','Zona 4',1);
call sp_AgregarCliente('Luis','Martinez','Zona 5',1);
call sp_AgregarCliente('Sofia','Hernandez','Zona 6',1);
call sp_AgregarCliente('Pedro','Diaz','Zona 7',1);
call sp_AgregarCliente('Lucia','Morales','Zona 8',1);
call sp_AgregarCliente('Jose','Castro','Zona 9',1);
call sp_AgregarCliente('Elena','Vargas','Zona 10',1);


delimiter $$
create procedure sp_EditarCliente(
	in p_dpi_cliente int,
	in p_nombre_cliente varchar(50),
    in p_apellido_cliente varchar(50),
    in p_direccion varchar (100),
    in p_estado int
)
begin
	update Clientes set nombre_cliente = p_nombre_cliente, apellido_cliente = p_apellido_cliente, direccion = p_direccion, estado = p_estado
    where dpi_cliente = p_dpi_cliente;
    
end $$
delimiter ;

delimiter $$
create procedure sp_ListarClientes()
begin
	select * from Clientes order by dpi_cliente;
end $$
delimiter ; 

delimiter $$
create procedure sp_EliminarClientes(
	in p_dpi_cliente int
)
begin
	delete from Clientes where dpi_cliente = p_dpi_cliente;
end $$
delimiter ;

-- ========= Productos ========= --
delimiter $$
create procedure sp_AgregarProductos(
	in p_nombre_producto varchar (60),
    in p_precio decimal (10,2),
    in p_stock int,
    in p_estado int

)
begin
	insert into Productos (nombre_producto, precio, stock, estado)
    values(p_nombre_producto, p_precio, p_stock, p_estado);

end $$
delimiter ;

call sp_AgregarProductos('Laptop',4500.00,10,1);
call sp_AgregarProductos('Mouse',50.00,100,1);
call sp_AgregarProductos('Teclado',120.00,50,1);
call sp_AgregarProductos('Monitor',1200.00,20,1);
call sp_AgregarProductos('Impresora',900.00,15,1);
call sp_AgregarProductos('USB 32GB',40.00,200,1);
call sp_AgregarProductos('Disco Duro',600.00,25,1);
call sp_AgregarProductos('Auriculares',150.00,60,1);
call sp_AgregarProductos('Webcam',300.00,30,1);
call sp_AgregarProductos('Silla Gamer',1800.00,10,1);


delimiter $$ 
create procedure sp_EditarProductos(
	in p_codigo_producto int,
    in p_nombre_producto varchar (60),
    in p_precio decimal (10,2),
    in p_stock int,
    in p_estado int
)
begin
	update Productos set nombre_producto = p_nombre_producto, precio = p_precio, stock = p_stock, estado = p_estado
    where codigo_producto = p_codigo_producto;

end $$
delimiter ;

delimiter $$
create procedure sp_ListarProductos()
begin
	select * from Productos order by codigo_producto;
end $$
delimiter ;

delimiter $$
create procedure sp_ELiminarProductos(
	in p_codigo_producto int
)
begin
	delete from Productos where codigo_producto = p_codigo_producto; 
end $$
delimiter ;

-- ========== Venta ========= --
delimiter $$
create procedure sp_AgregarVenta(
    in p_fecha_venta date,
    in p_total decimal(10,2),
    in p_estado int,
    in p_clientes_dpi_cliente int,
    in p_usuarios_codigo_usuario int
)
begin
    insert into Ventas(
        fecha_venta,
        total,
        estado,
        clientes_dpi_cliente,
        usuarios_codigo_usuario
    )
    values(
        p_fecha_venta,
        p_total,
        p_estado,
        p_clientes_dpi_cliente,
        p_usuarios_codigo_usuario
    );
end $$
delimiter ;
call sp_AgregarVenta('2026-01-01',500.00,1,1,1);
call sp_AgregarVenta('2026-01-02',1200.00,1,2,2);
call sp_AgregarVenta('2026-01-03',300.00,1,3,3);
call sp_AgregarVenta('2026-01-04',750.00,1,4,4);
call sp_AgregarVenta('2026-01-05',900.00,1,5,5);
call sp_AgregarVenta('2026-01-06',1500.00,1,6,6);
call sp_AgregarVenta('2026-01-07',200.00,1,7,7);
call sp_AgregarVenta('2026-01-08',650.00,1,8,8);
call sp_AgregarVenta('2026-01-09',1100.00,1,9,9);
call sp_AgregarVenta('2026-01-10',400.00,1,10,10);

delimiter $$
create procedure sp_EditarVenta(
    in p_codigo_venta int,
    in p_fecha_venta date,
    in p_total decimal(10,2),
    in p_estado int,
    in p_clientes_dpi_cliente int,
    in p_usuarios_codigo_usuario int
)
begin
    update Ventas 
    set fecha_venta = p_fecha_venta,
        total = p_total,
        estado = p_estado,
        clientes_dpi_cliente = p_clientes_dpi_cliente,
        usuarios_codigo_usuario = p_usuarios_codigo_usuario
    where codigo_venta = p_codigo_venta;
end $$
delimiter ;

delimiter $$
create procedure sp_ListarVentas()
begin
    select * from Ventas order by codigo_venta;
end $$
delimiter ;

delimiter $$
create procedure sp_EliminarVenta(
    in p_codigo_venta int
)
begin
    delete from Ventas where codigo_venta = p_codigo_venta;
end $$
delimiter ;

-- ============== DetalleVenta ============== - 
delimiter $$
create procedure sp_AgregarDetalleVenta(
    in p_cantidad int,
    in p_precio_unitario decimal(10,2),
    in p_subtotal decimal(10,2),
    in p_productos_codigo_producto int,
    in p_ventas_codigo_venta int
)
begin
    insert into Detalle_Venta(
        cantidad,
        precio_unitario,
        subtotal,
        productos_codigo_producto,
        ventas_codigo_venta
    )
    values(
        p_cantidad,
        p_precio_unitario,
        p_subtotal,
        p_productos_codigo_producto,
        p_ventas_codigo_venta
    );
end $$
delimiter ;
call sp_AgregarDetalleVenta(2,50.00,100.00,2,1);
call sp_AgregarDetalleVenta(1,4500.00,4500.00,1,2);
call sp_AgregarDetalleVenta(3,40.00,120.00,6,3);
call sp_AgregarDetalleVenta(1,1200.00,1200.00,4,4);
call sp_AgregarDetalleVenta(2,150.00,300.00,8,5);
call sp_AgregarDetalleVenta(1,900.00,900.00,5,6);
call sp_AgregarDetalleVenta(4,50.00,200.00,2,7);
call sp_AgregarDetalleVenta(1,600.00,600.00,7,8);
call sp_AgregarDetalleVenta(2,300.00,600.00,9,9);
call sp_AgregarDetalleVenta(1,1800.00,1800.00,10,10);

delimiter $$
create procedure sp_EditarDetalleVenta(
    in p_codigo_detalle_venta int,
    in p_cantidad int,
    in p_precio_unitario decimal(10,2),
    in p_subtotal decimal(10,2),
    in p_productos_codigo_producto int,
    in p_ventas_codigo_venta int
)
begin
    update Detalle_Venta
    set cantidad = p_cantidad,
        precio_unitario = p_precio_unitario,
        subtotal = p_subtotal,
        productos_codigo_producto = p_productos_codigo_producto,
        ventas_codigo_venta = p_ventas_codigo_venta
    where codigo_detalle_venta = p_codigo_detalle_venta;
end $$
delimiter ;

delimiter $$
create procedure sp_ListarDetalleVenta()
begin
    select * from Detalle_Venta order by codigo_detalle_venta;
end $$
delimiter ;

delimiter $$
create procedure sp_EliminarDetalleVenta(
    in p_codigo_detalle_venta int
)
begin
    delete from Detalle_Venta where codigo_detalle_venta = p_codigo_detalle_venta;
end $$
delimiter ;


