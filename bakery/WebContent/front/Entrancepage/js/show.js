$(function () {
    $(".btn").show();
    $(".btn  a:first").addClass("active");
    $().gallery({
        current: [".show_images_1", ".show_images_1_img"],
        left: [".show_images_2", ".show_images_2_img"],
        right: [".show_images_3", ".show_images_3_img"],
        none: [".show_images_4", ".show_images_4_img"],
        duration: 600,
        start: function () {
            $(".header_text").fadeOut(150);
        },
        end: function () {
            $(".header_text").fadeIn(150);
        },
        autoChange: true,
        changeTimeout: 3000,
        stopTarget: ".show_images_list_li"
    });
});

/*current：旋转木马的当前图片。
left：旋转木马的左边显示的图片。
right：旋转木马的右边显示的图片。
none：没有被显示的旋转木马的图片。
duration：动画的持续时间。
start：旋转木马的一张图片开始动画时的回调函数。
end：旋转木马的一张图片结束动画时的回调函数。
autoChange：是否自动播放。
changeTimeout：自动播放模式下图片切换的间隔时间。
stopTarget：自动播放模式下当鼠标悬停在图片上时暂停自动播放。*/