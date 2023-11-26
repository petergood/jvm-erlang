package org.jvmerlang.beam;

public enum OpCode {
    op_label(1, 1),
    op_func_info(2, 3),
    op_int_code_end(3, 0),
    op_call(4, 2),
    op_call_last(5, 3),
    op_call_only(6, 2),
    op_call_ext(7, 2),
    op_call_ext_last(8, 3),
    op_bif0(9, 2),
    op_bif1(10, 4),
    op_bif2(11, 5),
    op_allocate(12, 2),
    op_allocate_heap(13, 3),
    op_allocate_zero(14, 2),
    op_allocate_heap_zero(15, 3),
    op_test_heap(16, 2),
    op_init(17, 1),
    op_deallocate(18, 1),
    op_return(19, 0),
    op_send(20, 0),
    op_remove_message(21, 0),
    op_timeout(22, 0),
    op_loop_rec(23, 2),
    op_loop_rec_end(24, 1),
    op_wait(25, 1),
    op_wait_timeout(26, 2),
    op_m_plus(27, 4),
    op_m_minus(28, 4),
    op_m_times(29, 4),
    op_m_div(30, 4),
    op_int_div(31, 4),
    op_int_rem(32, 4),
    op_int_band(33, 4),
    op_int_bor(34, 4),
    op_int_bxor(35, 4),
    op_int_bsl(36, 4),
    op_int_bsr(37, 4),
    op_int_bnot(38, 3),
    op_is_lt(39, 3),
    op_is_ge(40, 3),
    op_is_eq(41, 3),
    op_is_ne(42, 3),
    op_is_eq_exact(43, 3),
    op_is_ne_exact(44, 3),
    op_is_integer(45, 2),
    op_is_float(46, 2),
    op_is_number(47, 2),
    op_is_atom(48, 2),
    op_is_pid(49, 2),
    op_is_reference(50, 2),
    op_is_port(51, 2),
    op_is_nil(52, 2),
    op_is_binary(53, 2),
    op_is_constant(54, 2),
    op_is_list(55, 2),
    op_is_nonempty_list(56, 2),
    op_is_tuple(57, 2),
    op_test_arity(58, 3),
    op_select_val(59, 3),
    op_select_tuple_arity(60, 3),
    op_jump(61, 1),
    op_catch(62, 2),
    op_catch_end(63, 1),
    op_move(64, 2),
    op_get_list(65, 3),
    op_get_tuple_element(66, 3),
    op_set_tuple_element(67, 3),
    op_put_string(68, 3),
    op_put_list(69, 3),
    op_put_tuple(70, 2),
    op_put(71, 1),
    op_badmatch(72, 1),
    op_if_end(73, 0),
    op_case_end(74, 1),
    op_call_fun(75, 1),
    op_make_fun(76, 3),
    op_is_function(77, 2),
    op_call_ext_only(78, 2),
    op_bs_start_match(79, 2),
    op_bs_get_integer(80, 5),
    op_bs_get_float(81, 5),
    op_bs_get_binary(82, 5),
    op_bs_skip_bits(83, 4),
    op_bs_test_tail(84, 2),
    op_bs_save(85, 1),
    op_bs_restore(86, 1),
    op_bs_init(87, 2),
    op_bs_final(88, 2),
    op_bs_put_integer(89, 5),
    op_bs_put_binary(90, 5),
    op_bs_put_float(91, 5),
    op_bs_put_string(92, 2),
    op_bs_need_buf(93, 1),
    op_fclearerror(94, 0),
    op_fcheckerror(95, 1),
    op_fmove(96, 2),
    op_fconv(97, 2),
    op_fadd(98, 4),
    op_fsub(99, 4),
    op_fmul(100, 4),
    op_fdiv(101, 4),
    op_fnegate(102, 3),
    op_make_fun2(103, 1),
    op_try(104, 2),
    op_try_end(105, 1),
    op_try_case(106, 1),
    op_try_case_end(107, 1),
    op_raise(108, 2),
    op_bs_init2(109, 6),
    op_bs_bits_to_bytes(110, 3),
    op_bs_add(111, 5),
    op_apply(112, 1),
    op_apply_last(113, 2),
    op_is_boolean(114, 2),
    op_is_function2(115, 3),
    op_bs_start_match2(116, 5),
    op_bs_get_integer2(117, 7),
    op_bs_get_float2(118, 7),
    op_bs_get_binary2(119, 7),
    op_bs_skip_bits2(120, 5),
    op_bs_test_tail2(121, 3),
    op_bs_save2(122, 2),
    op_bs_restore2(123, 2),
    op_gc_bif1(124, 5),
    op_gc_bif2(125, 6),
    op_bs_final2(126, 2),
    op_bs_bits_to_bytes2(127, 2),
    op_put_literal(128, 2),
    op_is_bitstr(129, 2),
    op_bs_context_to_binary(130, 1),
    op_bs_test_unit(131, 3),
    op_bs_match_string(132, 4),
    op_bs_init_writable(133, 0),
    op_bs_append(134, 8),
    op_bs_private_append(135, 6),
    op_trim(136, 2),
    op_bs_init_bits(137, 6),
    op_bs_get_utf8(138, 5),
    op_bs_skip_utf8(139, 4),
    op_bs_get_utf16(140, 5),
    op_bs_skip_utf16(141, 4),
    op_bs_get_utf32(142, 5),
    op_bs_skip_utf32(143, 4),
    op_bs_utf8_size(144, 3),
    op_bs_put_utf8(145, 3),
    op_bs_utf16_size(146, 3),
    op_bs_put_utf16(147, 3),
    op_bs_put_utf32(148, 3),
    op_on_load(149, 0),
    op_recv_mark(150, 1),
    op_recv_set(151, 1),
    op_gc_bif3(152, 7),
    op_line(153, 1),
    op_put_map_assoc(154, 5),
    op_put_map_exact(155, 5),
    op_is_map(156, 2),
    op_has_map_fields(157, 3),
    op_get_map_elements(158, 3),
    op_is_tagged_tuple(159, 4),
    op_build_stacktrace(160, 0),
    op_raw_raise(161, 0),
    op_get_hd(162, 2),
    op_get_tl(163, 2),
    op_put_tuple2(164, 2),
    op_bs_get_tail(165, 3),
    op_bs_start_match3(166, 4),
    op_bs_get_position(167, 3),
    op_bs_set_position(168, 2),
    op_swap(169, 2),
    op_bs_start_match4(170, 4),
    op_make_fun3(171, 3),
    op_init_yregs(172, 1),
    op_recv_marker_bind(173, 2),
    op_recv_marker_clear(174, 1),
    op_recv_marker_reserve(175, 1),
    op_recv_marker_use(176, 1),
    op_bs_create_bin(177, 6),
    op_call_fun2(178, 3),
    op_nif_start(179, 0),
    op_badrecord(180, 1),
    op_update_record(181, 5),
    op_bs_match(182, 3),
    op_executable_line(183, 1);

    int id;
    int arity;

    OpCode(int id, int arity) {
        this.id = id;
        this.arity = arity;
    }
}