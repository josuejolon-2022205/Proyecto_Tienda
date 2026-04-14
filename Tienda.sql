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

create table detalle_venta(
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

call sp_AgregarUsuario('admin01',     'Admin@2024',   'admin01@tienda.com',       'Administrador', 1);
call sp_AgregarUsuario('vendedor01',  'Vend@1234',    'vendedor01@tienda.com',    'Vendedor',      1);
call sp_AgregarUsuario('vendedor02',  'Vend@5678',    'vendedor02@tienda.com',    'Vendedor',      1);
call sp_AgregarUsuario('cajero01',    'Caj@1111',     'cajero01@tienda.com',      'Cajero',        1);
call sp_AgregarUsuario('cajero02',    'Caj@2222',     'cajero02@tienda.com',      'Cajero',        1);
call sp_AgregarUsuario('supervisor1', 'Sup@3333',     'supervisor1@tienda.com',   'Supervisor',    1);
call sp_AgregarUsuario('bodeguero01', 'Bod@4444',     'bodeguero01@tienda.com',   'Bodeguero',     1);
call sp_AgregarUsuario('gerente01',   'Ger@5555',     'gerente01@tienda.com',     'Gerente',       1);
call sp_AgregarUsuario('vendedor03',  'Vend@9999',    'vendedor03@tienda.com',    'Vendedor',      0);
call sp_AgregarUsuario('soporte01',   'Sop@7777',     'soporte01@tienda.com',     'Soporte',       1);


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
    values(p_nombre_cliente, p_apellido_cliente, p_direccion, p_estado);
end $$
delimiter ;

call sp_AgregarCliente('Carlos',     'Mendoza',    'Zona 1, Ciudad de Guatemala',   1);
call sp_AgregarCliente('María',      'López',      'Zona 10, Ciudad de Guatemala',  1);
call sp_AgregarCliente('José',       'García',     'Zona 4, Mixco',                 1);
call sp_AgregarCliente('Ana',        'Hernández',  'Zona 3, Villa Nueva',           1);
call sp_AgregarCliente('Luis',       'Martínez',   'Zona 7, Ciudad de Guatemala',   1);
call sp_AgregarCliente('Rosa',       'Pérez',      'Zona 2, Quetzaltenango',        1);
call sp_AgregarCliente('Pedro',      'Ramírez',    'Zona 5, Escuintla',             1);
call sp_AgregarCliente('Sofía',      'Torres',     'Zona 6, Antigua Guatemala',     1);
call sp_AgregarCliente('Fernando',   'Castillo',   'Zona 8, Petapa',                0);
call sp_AgregarCliente('Gabriela',   'Flores',     'Zona 9, San Juan Sacatepéquez', 1);

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

call sp_AgregarProductos('Laptop HP 15.6"',          4500.00, 20,  1);
call sp_AgregarProductos('Mouse Inalámbrico Logitech', 150.00, 80,  1);
call sp_AgregarProductos('Teclado Mecánico Redragon',  350.00, 50,  1);
call sp_AgregarProductos('Monitor Samsung 24"',       2200.00, 15,  1);
call sp_AgregarProductos('Memoria USB 64GB Kingston',   90.00, 120, 1);
call sp_AgregarProductos('Disco Duro Externo 1TB',     650.00, 30,  1);
call sp_AgregarProductos('Audífonos Sony WH-1000',    1200.00, 25,  1);
call sp_AgregarProductos('Webcam Logitech C920',       780.00, 18,  1);
call sp_AgregarProductos('Cable HDMI 3m',               45.00, 200, 1);
call sp_AgregarProductos('Hub USB 4 Puertos',          120.00, 60,  0);

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
    in p_total decimal (10,2),
    in p_estado int,
    in p_clientes_dpi_cliente int,
	in p_usuarios_codigo_usuario int 
)
begin
	insert into Ventas(fecha_venta, total, estado, clientes_dpi_cliente, usuarios_codigo_usuario)
    values(p_fecha_venta, p_total, p_estado, p_clientes_dpi_cliente, p_usuarios_codigo_usuario);

end $$
delimiter ; 

call sp_AgregarVenta('2024-01-10',  4650.00, 1, 1, 1);
call sp_AgregarVenta('2024-01-15',   300.00, 1, 2, 2);
call sp_AgregarVenta('2024-01-20',  2550.00, 1, 3, 3);
call sp_AgregarVenta('2024-02-05',  4500.00, 1, 4, 4);
call sp_AgregarVenta('2024-02-12',   830.00, 1, 5, 5);
call sp_AgregarVenta('2024-02-18',  1200.00, 1, 6, 6);
call sp_AgregarVenta('2024-03-01',  6700.00, 1, 7, 7);
call sp_AgregarVenta('2024-03-10',   195.00, 1, 8, 8);
call sp_AgregarVenta('2024-03-22',  3000.00, 0, 9, 9);
call sp_AgregarVenta('2024-04-01',   780.00, 1, 10, 10);

delimiter $$
create procedure sp_EditarVenta(
	in p_codigo_venta int,
    in p_fecha_venta date,
    in p_total decimal (10,2),
    in p_estado int,
    in p_clientes_dpi_cliente int,
    in p_usuarios_codigo_usuario int
)
begin
	update Ventas set fecha_venta = p_fecha_venta, total = p_total, estado = p_estado, clientes_dpi_cliente = p_clientes_dpi_cliente, usuarios_codigo_usuario = p_usuarios_codigo_usuario
    where codigo_venta = p_codigo_venta;
end $$
delimiter ;

delimiter $$
create procedure sp_ListarVenta()
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

-- ============ Detalles Venta ============== --

delimiter $$
create procedure sp_AgregarDetalleVenta(
	in p_cantidad int,
    in p_precio_unitario decimal(10,2),
    in p_subtotal decimal (10,2),
    in p_productos_codigo_producto int,
    in p_ventas_codigo_venta int

)
begin
	insert into detalle_venta(cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
    values(p_cantidad, p_precio_unitario, p_subtotal, p_productos_codigo_producto, p_ventas_codigo_venta);
end $$
delimiter ;

call sp_AgregarDetalleVenta(1,  4500.00, 4500.00, 1,  1);
call sp_AgregarDetalleVenta(1,   150.00,  150.00, 2,  1);
call sp_AgregarDetalleVenta(2,   150.00,  300.00, 2,  2);
call sp_AgregarDetalleVenta(1,  2200.00, 2200.00, 4,  3);
call sp_AgregarDetalleVenta(2,   175.00,  350.00, 3,  3);
call sp_AgregarDetalleVenta(1,  4500.00, 4500.00, 1,  4);
call sp_AgregarDetalleVenta(1,   780.00,  780.00, 8,  5);
call sp_AgregarDetalleVenta(1,   650.00,  650.00, 6,  5);
call sp_AgregarDetalleVenta(1,  1200.00, 1200.00, 7,  6);
call sp_AgregarDetalleVenta(3,    90.00,  270.00, 5,  7);

delimiter $$
create procedure sp_EditarDetalleVenta(
	in p_codigo_detalle_venta int,
    in p_cantidad int,
    in p_precio_unitario decimal(10,2),
    in p_subtotal decimal (10,2),
    in p_productos_codigo_producto int,
    in p_ventas_codigo_venta int
    
)
begin
	update detalle_venta set cantidad = p_cantidad, precio_unitario = p_precio_unitario, subtotal = p_subtotal, productos_codigo_producto = p_productos_codigo_producto, ventas_codigo_venta = p_ventas_codigo_venta
    where codigo_detalle_venta = p_codigo_detalle_venta; 

end $$
delimiter ;

delimiter $$
create procedure sp_ListarDetalleVenta()
begin
	select * from detalle_venta;

end $$
delimiter ;

delimiter $$
create procedure sp_EliminarDetalleVenta(
	in p_codigo_detalle_venta int
)
begin
	delete from detalle_venta where codigo_detalle_venta = p_codigo_detalle_venta;

end $$
delimiter ;

