import React from "react";

const CommentSection = () => {
    return null;
    // <section>
    //         <CommentBox id={id} />

    //         {/* comment item */}
    //             <div className="mb-4">
    //                 {data?.comments?.map((comment) => (
    //                     <div key={comment._id}>
    //                         <div className={cx("comment-item")}>
    //                             <div className={cx("comment-item__avatar")}>
    //                                 <span>{comment.email.slice(0, 2)}</span>
    //                             </div>
    //                             <div className={cx("comment-item__body")}>
    //                                 <div className={cx("name")}>
    //                                     <p>
    //                                         {comment.user} &nbsp;-&nbsp;
    //                                         <span>
    //                                             {dFormat(
    //                                                 new Date(comment.created)
    //                                             )}
    //                                         </span>
    //                                         <span className={cx("reply-btn")}>
    //                                             Trả lời
    //                                         </span>
    //                                     </p>
    //                                 </div>

    //                                 <div className={cx("content")}>
    //                                     {comment.content}
    //                                 </div>
    //                             </div>
    //                         </div>

    //                         {comment.reply.map((rep: any, index) => (
    //                             <div
    //                                 className={cx("comment-reply")}
    //                                 key={index}
    //                             >
    //                                 <div className={cx("name")}>
    //                                     <p>{rep.name}</p>
    //                                     &nbsp;-&nbsp;
    //                                     <span>
    //                                         {dFormat(new Date(rep.created))}
    //                                     </span>
    //                                 </div>
    //                                 <div className={cx("content")}>
    //                                     {rep.content}
    //                                 </div>
    //                             </div>
    //                         ))}
    //                     </div>
    //                 ))}
    //             </div>
    //     </section>
};

export default CommentSection;
