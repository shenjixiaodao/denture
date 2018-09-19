package com.yuzi.denture.domain;

public enum ReviewResult {

        Accept("同意"), Reject("拒绝");
        private String text;
        ReviewResult(String text) {
                this.text = text;
        }
        public String text() {
                return this.text;
        }

        public static ReviewResult typeOf(String type) {
                for(ReviewResult result : ReviewResult.values()){
                        if(result.name().toLowerCase().equals(type.toLowerCase()))
                                return result;
                }
                throw new IllegalArgumentException("未知审核类型");
        }
}
